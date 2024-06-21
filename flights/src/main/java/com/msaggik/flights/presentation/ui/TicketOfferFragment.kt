package com.msaggik.flights.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.msaggik.flights.R
import com.msaggik.flights.databinding.FragmentPlaceholderBinding
import com.msaggik.flights.databinding.FragmentTicketOfferBinding

private const val ARRIVAL_POINT_KEY = "ARRIVAL_POINT_KEY"
private const val DEPARTURE_POINT_KEY = "DEPARTURE_POINT_KEY"
class TicketOfferFragment : Fragment() {

    private var _binding: FragmentTicketOfferBinding? = null
    private val binding: FragmentTicketOfferBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTicketOfferBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewPlacesArguments()

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setViewPlacesArguments() {
        val arrivalPlace = arguments?.getString(ARRIVAL_POINT_KEY)
        val departurePlace = arguments?.getString(DEPARTURE_POINT_KEY)

        Log.e("data", "$arrivalPlace, $departurePlace")

        binding.arrivalPoint.setText(arrivalPlace)
        binding.departurePoint.setText(departurePlace)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}