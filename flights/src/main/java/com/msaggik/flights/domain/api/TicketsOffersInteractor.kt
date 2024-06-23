package com.msaggik.flights.domain.api

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.model.Offer
import com.msaggik.flights.domain.model.TicketOffer

interface TicketsOffersInteractor {

    fun getTicketsOffers(consumer: TicketsOffersConsumer)

    interface TicketsOffersConsumer {
        fun consume(listTicketsOffers: List<TicketOffer>?, errorMessage: String?)
    }
}