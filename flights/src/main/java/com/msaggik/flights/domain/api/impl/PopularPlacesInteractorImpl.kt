package com.msaggik.flights.domain.api.impl

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.api.PopularPlacesInteractor
import com.msaggik.flights.domain.model.PopularPlaces
import com.msaggik.flights.domain.repository.PopularPlacesRepository
import java.util.concurrent.Executors

class PopularPlacesInteractorImpl(
    private val repository: PopularPlacesRepository
) : PopularPlacesInteractor {

    override fun getPopularPlaces(consumer: PopularPlacesInteractor.PopularPlacesConsumer) {
        Executors.newCachedThreadPool().execute {
            consumer.consume(repository.getPopularPlacesDomain())
        }
    }

}