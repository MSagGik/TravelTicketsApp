package com.msaggik.flights.domain.api.impl

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.api.OffersInteractor
import com.msaggik.flights.domain.api.PopularPlacesInteractor
import com.msaggik.flights.domain.model.Offer
import com.msaggik.flights.domain.model.PopularPlaces
import com.msaggik.flights.domain.repository.OffersRepository
import com.msaggik.flights.domain.repository.PopularPlacesRepository

class OffersInteractorImpl(
    private val repository: OffersRepository
) : OffersInteractor {
    override fun getOffers(): Resource<List<Offer>> {
        return repository.getOffersDomain()
    }
}