package com.msaggik.flights.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msaggik.flights.domain.api.OffersInteractor
import com.msaggik.flights.domain.api.PopularPlacesInteractor
import com.msaggik.flights.domain.model.Offer
import com.msaggik.flights.domain.model.PopularPlaces
import com.msaggik.flights.presentation.view_model.state.OffersState

class PopularPlacesViewModel (
    private val popularPlacesInteractor: PopularPlacesInteractor
) : ViewModel() {

    init {
        readPopularPlaces()
    }

    private val popularPlacesLiveData = MutableLiveData<List<PopularPlaces>>()
    fun getPopularPlacesLiveData(): LiveData<List<PopularPlaces>> = popularPlacesLiveData

    private fun readPopularPlaces() {
        popularPlacesInteractor.getPopularPlaces(object : PopularPlacesInteractor.PopularPlacesConsumer {
            override fun consume(listPopularPlaces: List<PopularPlaces>?) {
                popularPlacesLiveData.postValue(listPopularPlaces)
            }
        })
    }
}