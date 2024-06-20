package com.msaggik.flights.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.msaggik.common_util.Utils
import com.msaggik.flights.databinding.FragmentFlightsBinding
import com.msaggik.flights.domain.model.Offer
import com.msaggik.flights.domain.model.PopularPlaces
import com.msaggik.flights.presentation.ui.adapters.OfferAdapter
import com.msaggik.flights.presentation.ui.adapters.PopularPlacesAdapter
import com.msaggik.flights.presentation.view_model.OffersViewModel
import com.msaggik.flights.presentation.view_model.PopularPlacesViewModel
import com.msaggik.flights.presentation.view_model.state.OffersState
import org.koin.androidx.viewmodel.ext.android.viewModel

class FlightsFragment : Fragment() {

    companion object {
        fun newInstance() = FlightsFragment()
    }

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

        allFunOneField()
        allFunTwoField()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

//    One field

    private val offersViewModel: OffersViewModel by viewModel()

    private var listOffers: MutableList<Offer> = ArrayList()

    private val offerAdapter: OfferAdapter by lazy {
        OfferAdapter(listOffers)
    }

    private fun allFunOneField() {
        visibleFrameSearchContainer()
        viewMusicalRoute()
        observeLiveDataOffers()
        configurationView()
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
                } else if (p1 == BottomSheetBehavior.STATE_HIDDEN) {
                    isVisibleFrameSearchContainer = false
                }
            }
            override fun onSlide(p0: View, p1: Float) {}
        })
        if (isVisibleFrameSearchContainer) {
            frameSearchContainer.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun configurationView() {
        binding.arrivalPoint.setOnClickListener {
            binding.departurePointSearch.text = binding.departurePoint.text
            binding.departurePoint.clearFocus()
            Utils.closeKeyBoard(requireActivity(), binding.arrivalPoint)
            frameSearchContainer.state = BottomSheetBehavior.STATE_EXPANDED
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
        PopularPlacesAdapter(listPopularPlaces)
    }

    private fun allFunTwoField() {
        viewPopularPlaces()
        observeLiveDataPopularPlaces()
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


}