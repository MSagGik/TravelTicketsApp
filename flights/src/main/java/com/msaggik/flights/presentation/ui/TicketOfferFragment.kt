package com.msaggik.flights.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.CalendarView.OnDateChangeListener
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.msaggik.common_util.Utils
import com.msaggik.flights.databinding.FragmentTicketOfferBinding
import com.msaggik.flights.domain.model.Offer
import com.msaggik.flights.domain.model.SelectedTicket
import com.msaggik.flights.domain.model.TicketOffer
import com.msaggik.flights.presentation.ui.adapters.OfferAdapter
import com.msaggik.flights.presentation.ui.adapters.TicketOfferAdapter
import com.msaggik.flights.presentation.view_model.LastDeparturePlaceViewModel
import com.msaggik.flights.presentation.view_model.TicketOfferDbViewModel
import com.msaggik.flights.presentation.view_model.TicketsOffersViewModel
import com.msaggik.flights.presentation.view_model.state.OffersState
import com.msaggik.flights.presentation.view_model.state.TicketsOffersState
import okio.Utf8
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

private const val ARRIVAL_POINT_KEY = "arrival"
private const val DEPARTURE_POINT_KEY = "departure"
class TicketOfferFragment : Fragment() {

    private var _binding: FragmentTicketOfferBinding? = null
    private val binding: FragmentTicketOfferBinding get() = _binding!!

    private val arrivalReceived by lazy {
        arguments?.getString(ARRIVAL_POINT_KEY)
    }
    private val departureReceived by lazy {
        arguments?.getString(DEPARTURE_POINT_KEY)
    }

    private var flagDate: Boolean = true // true - calendarArrivalDate, false - calendarDepartureDate in dateChangeListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTicketOfferBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.arrivalPoint.setText(arrivalReceived)
        binding.departurePoint.setText(departureReceived)

        currentDepartureDate()
        viewTicketOffer()
        observeLiveDataTicketOffers()
        onListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val ticketsOffersViewModel: TicketsOffersViewModel by viewModel()
    private val lastDeparturePlaceViewModel: LastDeparturePlaceViewModel by viewModel()

    private var lastDeparturePlace: String = ""

    private var listTicketOffer: MutableList<TicketOffer> = ArrayList()

    private val adapterTicketOffer: TicketOfferAdapter by lazy {
        TicketOfferAdapter(listTicketOffer)
    }

    private fun viewTicketOffer() {
        binding.ticketOffers.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.ticketOffers.adapter = adapterTicketOffer
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeLiveDataTicketOffers() {
        ticketsOffersViewModel.getStateLiveData().observe(viewLifecycleOwner) {
            listTicketOffer.clear()
            renderTicketOffersState(it)
            adapterTicketOffer.notifyDataSetChanged()
        }
    }

    private fun renderTicketOffersState(state: TicketsOffersState) {
        when (state) {
            is TicketsOffersState.Loading -> binding.ticketOffers.visibility = View.GONE
            is TicketsOffersState.Content -> {
                binding.ticketOffers.visibility = View.VISIBLE
                listTicketOffer.addAll(state.ticketsOffers)
            }
            is TicketsOffersState.Empty -> binding.ticketOffers.visibility = View.GONE
            is TicketsOffersState.Error -> {
                binding.ticketOffers.visibility = View.GONE
                Toast.makeText(requireActivity(), state.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onListener() {

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.clear.setOnClickListener {
            binding.arrivalPoint.setText("")
            Utils.closeKeyBoard(requireActivity(), binding.arrivalPoint)
        }

        binding.reverse.setOnClickListener {
            lastDeparturePlace = binding.arrivalPoint.text.toString()
            binding.arrivalPoint.text = binding.departurePoint.text
            binding.departurePoint.setText(lastDeparturePlace)
            lastDeparturePlaceViewModel.setLastDeparturePlace(lastDeparturePlace)
        }

        binding.button.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.containerArrivalDate.setOnClickListener {
            binding.calendarView.visibility = View.VISIBLE
            flagDate = true
        }

        binding.containerDepartureDate.setOnClickListener {
            binding.calendarView.visibility = View.VISIBLE
            flagDate = false
        }

        binding.calendar.setOnDateChangeListener(dateChangeListener)
    }

    private val dateChangeListener: OnDateChangeListener = object : OnDateChangeListener {
        override fun onSelectedDayChange(p0: CalendarView, year: Int, month: Int, day: Int) {
            val (dayMonth, weekDay) = Utils.getFormatDay(year, month, day)
            if (flagDate) {
                binding.addDate.visibility = View.GONE
                binding.arrivalWeekDay.visibility = View.VISIBLE
                binding.arrivalDate.text = dayMonth
                binding.arrivalWeekDay.text = weekDay
            } else {
                binding.departureDate.text = dayMonth
                binding.departureWeekDay.text = weekDay
            }
            binding.calendarView.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    private fun currentDepartureDate() {
        val currentTime = Calendar.getInstance()
        val currentDay = currentTime[Calendar.DAY_OF_MONTH]
        val currentMonth = currentTime[Calendar.MONTH]
        val currentYear = currentTime[Calendar.YEAR]

        val (day, weekDay) = Utils.getFormatDay(currentYear, currentMonth, currentDay)

        binding.departureDate.text = day
        binding.departureWeekDay.text = weekDay
    }
}