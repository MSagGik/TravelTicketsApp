package com.msaggik.flights.domain.api.impl

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.api.OffersInteractor
import com.msaggik.flights.domain.api.PopularPlacesInteractor
import com.msaggik.flights.domain.model.Offer
import com.msaggik.flights.domain.model.PopularPlaces
import com.msaggik.flights.domain.repository.OffersRepository
import com.msaggik.flights.domain.repository.PopularPlacesRepository
import java.util.concurrent.Executors

class OffersInteractorImpl(
    private val repository: OffersRepository
) : OffersInteractor {

    override fun getOffers(consumer: OffersInteractor.OffersConsumer) {
        Executors.newCachedThreadPool().execute {
            when (val resource = repository.getOffersDomain()) {
                is Resource.Success -> { consumer.consume(resource.data, null)}
                is Resource.Error -> { consumer.consume(null, resource.message)}
            }
        }
    }
}