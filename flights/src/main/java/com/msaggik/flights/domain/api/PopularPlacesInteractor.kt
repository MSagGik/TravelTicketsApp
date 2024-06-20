package com.msaggik.flights.domain.api

import com.msaggik.flights.domain.model.PopularPlaces

interface PopularPlacesInteractor {
    fun getPopularPlaces(): List<PopularPlaces>
}