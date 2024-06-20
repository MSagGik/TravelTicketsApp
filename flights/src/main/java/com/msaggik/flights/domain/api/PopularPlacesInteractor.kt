package com.msaggik.flights.domain.api

import com.msaggik.flights.domain.model.PopularPlaces

interface PopularPlacesInteractor {

    fun getPopularPlaces(consumer: PopularPlacesConsumer)

    interface PopularPlacesConsumer {
        fun consume(listPopularPlaces: List<PopularPlaces>?)
    }
}