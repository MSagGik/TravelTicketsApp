package com.msaggik.flights.domain.api.impl

import com.msaggik.flights.domain.api.PopularPlacesInteractor
import com.msaggik.flights.domain.model.PopularPlaces
import com.msaggik.flights.domain.repository.PopularPlacesRepository

class PopularPlacesInteractorImpl(
    private val repository: PopularPlacesRepository
) : PopularPlacesInteractor {
    override fun getPopularPlaces(): List<PopularPlaces> {
        return repository.getPopularPlacesDomain()
    }
}