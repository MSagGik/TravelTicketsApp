package com.msaggik.flights.presentation.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.msaggik.common_util.Utils
import com.msaggik.common_ui.R
import com.msaggik.common_util.InputFilterCyrillic
import com.msaggik.flights.databinding.FragmentFlightsBinding
import com.msaggik.flights.domain.model.Offer
import com.msaggik.flights.domain.model.PopularPlaces
import com.msaggik.flights.presentation.ui.adapters.OfferAdapter
import com.msaggik.flights.presentation.ui.adapters.PopularPlacesAdapter
import com.msaggik.flights.presentation.view_model.LastDeparturePlaceViewModel
import com.msaggik.flights.presentation.view_model.OffersViewModel
import com.msaggik.flights.presentation.view_model.PopularPlacesViewModel
import com.msaggik.flights.presentation.view_model.state.OffersState
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val ARRIVAL_POINT_KEY = "ARRIVAL_POINT_KEY"
private const val DEPARTURE_POINT_KEY = "DEPARTURE_POINT_KEY"
class FlightsFragment : Fragment() {

    companion object {
        fun newInstance() = FlightsFragment()
    }

    private val filter = InputFilterCyrillic()

    private var _binding: FragmentFlightsBinding? = null
    private val binding: FragmentFlightsBinding get() = _binding!!

    private lateinit var frameSearchContainer: BottomSheetBehavior<FrameLayout>
    private var isVisibleFrameSearchContainer = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFlightsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFilterKeyBoard()
        allFunOneField()
        allFunTwoField()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setFilterKeyBoard() {
        binding.departurePoint.filters = arrayOf(filter)
        binding.departurePointSearch.filters = arrayOf(filter)
        binding.arrivalPoint.filters = arrayOf(filter)
        binding.arrivalPointSearch.filters = arrayOf(filter)
    }

//    One field

    private val offersViewModel: OffersViewModel by viewModel()
    private val lastDeparturePlaceViewModel: LastDeparturePlaceViewModel by viewModel()

    private var listOffers: MutableList<Offer> = ArrayList()
    private var lastDeparturePlace: String = ""

    private val offerAdapter: OfferAdapter by lazy {
        OfferAdapter(listOffers)
    }

    private fun allFunOneField() {
        visibleFrameSearchContainer()
        viewMusicalRoute()
        observeLiveDataLastDeparture()
        observeLiveDataOffers()
        configurationViewOneField()
    }

    private fun viewMusicalRoute() {
        binding.listMusicalRoute.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.listMusicalRoute.adapter = offerAdapter
    }

    private fun visibleFrameSearchContainer() {
        frameSearchContainer = BottomSheetBehavior.from(binding.frameSearchContainer).apply {
            state = BottomSheetBehavior.STATE_HIDDEN
        }
        frameSearchContainer.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(p0: View, p1: Int) {
                if (p1 == BottomSheetBehavior.STATE_EXPANDED) {
                    isVisibleFrameSearchContainer = true
                    binding.departurePoint.text = binding.departurePointSearch.text
                } else if (p1 == BottomSheetBehavior.STATE_HIDDEN) {
                    isVisibleFrameSearchContainer = false
                    binding.departurePointSearch.text = binding.departurePoint.text
                }
            }
            override fun onSlide(p0: View, p1: Float) {}
        })
        if (isVisibleFrameSearchContainer) {
            frameSearchContainer.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun configurationViewOneField() {
        binding.arrivalPoint.setOnClickListener {
            binding.departurePointSearch.text = binding.departurePoint.text
            binding.departurePoint.clearFocus()
            Utils.closeKeyBoard(requireActivity(), binding.arrivalPoint)
            frameSearchContainer.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun observeLiveDataLastDeparture() {
        lastDeparturePlaceViewModel.getLastDeparturePlaceLiveData().observe(viewLifecycleOwner) {
            lastDeparturePlace = it
            binding.departurePoint.setText(lastDeparturePlace)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeLiveDataOffers() {
        offersViewModel.getStateLiveData().observe(viewLifecycleOwner) {
            listOffers.clear()
            renderOffersState(it)
            offerAdapter.notifyDataSetChanged()
        }
    }

    private fun renderOffersState(state: OffersState) {
        when (state) {
            is OffersState.Loading -> binding.listMusicalRoute.visibility = View.GONE
            is OffersState.Content -> {
                binding.listMusicalRoute.visibility = View.VISIBLE
                listOffers.addAll(state.offers)
            }

            is OffersState.Empty -> binding.listMusicalRoute.visibility = View.GONE
            is OffersState.Error -> {
                binding.listMusicalRoute.visibility = View.GONE
                Toast.makeText(requireActivity(), state.errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    //    Two field

    private val popularPlacesViewModel: PopularPlacesViewModel by viewModel()

    private var listPopularPlaces: MutableList<PopularPlaces> = ArrayList()

    private val popularPlacesAdapter: PopularPlacesAdapter by lazy {
        PopularPlacesAdapter(listPopularPlaces) {
            binding.arrivalPointSearch.setText(it.town)
            toNextFragment()
        }
    }

    private fun allFunTwoField() {
        viewPopularPlaces()
        observeLiveDataPopularPlaces()
        configurationViewTwoField()
        listenerToNextFragment()
    }

    private fun viewPopularPlaces() {
        binding.popularPlaces.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.popularPlaces.adapter = popularPlacesAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeLiveDataPopularPlaces() {
        popularPlacesViewModel.getPopularPlacesLiveData().observe(viewLifecycleOwner) {
            listPopularPlaces.clear()
            listPopularPlaces.addAll(it)
            popularPlacesAdapter.notifyDataSetChanged()
        }
    }

    private fun configurationViewTwoField() {
        binding.departurePoint.onFocusChangeListener = onFocusChangeListener
        binding.departurePointSearch.onFocusChangeListener = onFocusChangeListener
        binding.arrivalPointSearch.addTextChangedListener(textWatcherArrivalListener)
        binding.clear.setOnClickListener {
            binding.arrivalPointSearch.text?.clear()
            Utils.closeKeyBoard(requireActivity(), binding.arrivalPointSearch)
            binding.arrivalPointSearch.clearFocus()
        }

        binding.difficultRoute.setOnClickListener{
            onPlaceholderFragment()
        }
        binding.anywhere.setOnClickListener{
            binding.arrivalPointSearch.setText(activity?.getString(R.string.anywhere))
            toNextFragment()
        }
        binding.weekend.setOnClickListener{
            onPlaceholderFragment()
        }
        binding.lastMinuteTickets.setOnClickListener{
            onPlaceholderFragment()
        }
    }

    private fun onPlaceholderFragment() {
        findNavController().navigate(com.msaggik.flights.R.id.action_flightsFragment_to_placeholderFragment)
    }

    private val onFocusChangeListener = object : OnFocusChangeListener {
        override fun onFocusChange(p0: View?, p1: Boolean) {
            if(!p1) {
                if(p0?.id == binding.departurePoint.id) {
                    lastDeparturePlace = binding.departurePoint.text.toString()
                } else if(p0?.id == binding.departurePointSearch.id) {
                    lastDeparturePlace = binding.departurePointSearch.text.toString()
                }
                lastDeparturePlaceViewModel.setLastDeparturePlace(lastDeparturePlace)
            }
        }
    }

    private val textWatcherArrivalListener = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (p0.isNullOrEmpty()) {
                binding.clear.visibility = View.GONE
            } else {
                binding.clear.visibility = View.VISIBLE
            }
        }

        override fun afterTextChanged(p0: Editable?) {

        }
    }

    private fun listenerToNextFragment() {
        binding.arrivalPointSearch.setOnEditorActionListener { textView, id, keyEvent ->
            toNextFragment()
            true
        }
    }

    private fun toNextFragment() {
        val bundle = Bundle()
        bundle.putString(ARRIVAL_POINT_KEY, binding.arrivalPointSearch.text.toString())
        bundle.putString(DEPARTURE_POINT_KEY, binding.departurePointSearch.text.toString())
        arguments = bundle
        findNavController().navigate(com.msaggik.flights.R.id.action_flightsFragment_to_ticketOfferFragment)
    }
}