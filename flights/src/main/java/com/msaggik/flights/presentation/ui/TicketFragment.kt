package com.msaggik.flights.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.msaggik.flights.databinding.FragmentTicketBinding

private const val ARRIVAL_POINT_KEY = "arrival_point"
private const val DEPARTURE_POINT_KEY = "departure_point"
private const val DEPARTURE_DATE_KEY = "departure_date"
private const val NUMBER_OF_PASSENGERS_KEY = "number_of_passengers"
class TicketFragment : Fragment() {

    private var _binding: FragmentTicketBinding? = null
    private val binding: FragmentTicketBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTicketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputData()
        onListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("SetTextI18n")
    private fun inputData() {
        val arrivalPointReceived = arguments?.getString(ARRIVAL_POINT_KEY)
        val departurePointReceived = arguments?.getString(DEPARTURE_POINT_KEY)
        val departureDateReceived = arguments?.getString(DEPARTURE_DATE_KEY)
        val numberOfPassengersReceived = arguments?.getInt(NUMBER_OF_PASSENGERS_KEY)
        binding.direction.text = "$departurePointReceived-$arrivalPointReceived"
        binding.descriptionDirection.text = "$departureDateReceived, $numberOfPassengersReceived пассажир"
    }

    private fun onListener() {
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}