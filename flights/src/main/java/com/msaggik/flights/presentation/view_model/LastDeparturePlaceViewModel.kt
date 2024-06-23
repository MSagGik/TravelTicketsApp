package com.msaggik.flights.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msaggik.flights.domain.api.LastDeparturePlaceInteractor

class LastDeparturePlaceViewModel (
    private val lastDeparturePlaceInteractor: LastDeparturePlaceInteractor
) : ViewModel() {

    init {
        lastDeparturePlace()
    }

    private val lastDeparturePlaceLiveData = MutableLiveData<String>()
    fun getLastDeparturePlaceLiveData(): LiveData<String> = lastDeparturePlaceLiveData

    private fun lastDeparturePlace() {
        lastDeparturePlaceInteractor.getLastDeparturePlace(object : LastDeparturePlaceInteractor.LastDeparturePlaceConsumer {
            override fun consume(departurePlace: String) {
                lastDeparturePlaceLiveData.postValue(departurePlace)
            }
        })
    }

    fun setLastDeparturePlace(departurePlace: String) {
        lastDeparturePlaceInteractor.setLastDeparturePlace(
            departurePlace,
            object : LastDeparturePlaceInteractor.LastDeparturePlaceConsumer {
                override fun consume(departurePlace: String) {
                    lastDeparturePlaceLiveData.postValue(departurePlace)
                }
            })
    }
}