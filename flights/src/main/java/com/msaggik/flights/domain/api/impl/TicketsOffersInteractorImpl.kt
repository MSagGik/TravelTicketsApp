package com.msaggik.flights.domain.api.impl

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.api.TicketsOffersInteractor
import com.msaggik.flights.domain.model.TicketOffer
import com.msaggik.flights.domain.repository.TicketsOffersRepository

class TicketsOffersInteractorImpl(
    private val repository: TicketsOffersRepository
) : TicketsOffersInteractor {
    override fun getTicketsOffers(): Resource<List<TicketOffer>> {
        return repository.getTicketsOffersDomain()
    }
}