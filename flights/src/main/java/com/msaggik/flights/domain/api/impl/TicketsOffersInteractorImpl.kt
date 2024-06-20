package com.msaggik.flights.domain.api.impl

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.api.TicketsOffersInteractor
import com.msaggik.flights.domain.model.TicketOffer
import com.msaggik.flights.domain.repository.TicketsOffersRepository
import java.util.concurrent.Executors

class TicketsOffersInteractorImpl(
    private val repository: TicketsOffersRepository
) : TicketsOffersInteractor {

    override fun getTicketsOffers(consumer: TicketsOffersInteractor.TicketsOffersConsumer) {
        Executors.newCachedThreadPool().execute {
            when (val resource = repository.getTicketsOffersDomain()) {
                is Resource.Success -> { consumer.consume(resource.data, null)}
                is Resource.Error -> { consumer.consume(null, resource.message)}
            }
        }
    }
}