package com.msaggik.flights.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.msaggik.common_ui.R
import com.msaggik.flights.databinding.FragmentTicketBinding
import com.msaggik.flights.domain.model.SelectedTicket
import com.msaggik.flights.domain.model.Ticket
import com.msaggik.flights.presentation.ui.adapters.TicketsAdapter
import com.msaggik.flights.presentation.view_model.TicketDbViewModel
import com.msaggik.flights.presentation.view_model.TicketsViewModel
import com.msaggik.flights.presentation.view_model.state.TicketsState
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARRIVAL_POINT_KEY = "arrival_point"
private const val DEPARTURE_POINT_KEY = "departure_point"
private const val DEPARTURE_DATE_KEY = "departure_date"
private const val NUMBER_OF_PASSENGERS_KEY = "number_of_passengers"
private const val HAS_TRANSFER_KEY = "hasTransfer"
private const val LUGGAGE_KEY = "luggage"
class TicketFragment : Fragment() {

    private var _binding: FragmentTicketBinding? = null
    private val binding: FragmentTicketBinding get() = _binding!!

    private val arrivalPointReceived by lazy { arguments?.getString(ARRIVAL_POINT_KEY) }
    private val departurePointReceived by lazy { arguments?.getString(DEPARTURE_POINT_KEY) }
    private val departureDateReceived by lazy { arguments?.getString(DEPARTURE_DATE_KEY) }
    private val numberOfPassengersReceived by lazy { arguments?.getInt(NUMBER_OF_PASSENGERS_KEY) }
    private val hasTransferReceived by lazy { arguments?.getInt(HAS_TRANSFER_KEY) }
    private val luggageReceived by lazy { arguments?.getInt(LUGGAGE_KEY) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputData()
        viewTickets()
        observeLiveDataTickets()
        onListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    private fun inputData() {
        binding.direction.text = "$departurePointReceived-$arrivalPointReceived"
        binding.descriptionDirection.text = getString(R.string.number_of_passengers, departureDateReceived, numberOfPassengersReceived)
    }

    private fun onListener() {
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private val ticketsViewModel: TicketsViewModel by viewModel()
    private val ticketDbViewModel: TicketDbViewModel by viewModel()

    private var listTickets: MutableList<Ticket> = ArrayList()

    private val adapterTickets: TicketsAdapter by lazy {
        TicketsAdapter(listTickets) {
            sendToDbDataTicket(it)
        }
    }

    private fun sendToDbDataTicket(ticket: Ticket) {
        val selectedTicket = with(ticket) {
            SelectedTicket(
                idServer = id,
                departure = departurePointReceived,
                arrival = arrivalPointReceived,
                departureTime = departureDateReceived,
                numberPassengers = numberOfPassengersReceived,
                arrivalAirportCode = arrivalAirportCode,
                departureAirportCode = departureAirportCode,
                hasTransfer = hasTransfer,
                badge = badge,
                price = price
            )
        }
        Toast.makeText(requireActivity(), getString(R.string.message_add_db), Toast.LENGTH_LONG).show()
        ticketDbViewModel.setTicketLiveData(selectedTicket) // save SelectedTicket in data base
    }

    private fun viewTickets() {
        binding.listTickets.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.listTickets.adapter = adapterTickets
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeLiveDataTickets() {
        ticketsViewModel.getStateLiveData().observe(viewLifecycleOwner) {
            listTickets.clear()
            renderTicketsState(it)
            adapterTickets.notifyDataSetChanged()
        }
    }

    private fun renderTicketsState(state: TicketsState) {
        when (state) {
            is TicketsState.Loading -> binding.listTickets.visibility = View.GONE
            is TicketsState.Content -> {
                binding.listTickets.visibility = View.VISIBLE

                val tickets: List<Ticket> = when {
                    hasTransferReceived == 0 && luggageReceived == 0 ->  state.tickets.filter { !it.luggage && !it.hasTransfer }
                    hasTransferReceived == 0 && luggageReceived == 1 ->  state.tickets.filter { !it.luggage && it.hasTransfer }
                    hasTransferReceived == 1 && luggageReceived == 1 ->  state.tickets.filter { it.luggage && it.hasTransfer }
                    else -> state.tickets
                }

                listTickets.addAll(tickets)
            }
            is TicketsState.Empty -> binding.listTickets.visibility = View.GONE
            is TicketsState.Error -> {
                binding.listTickets.visibility = View.GONE
                Toast.makeText(requireActivity(), state.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}