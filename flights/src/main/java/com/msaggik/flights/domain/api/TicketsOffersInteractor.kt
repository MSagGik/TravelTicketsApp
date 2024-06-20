package com.msaggik.flights.domain.api

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.model.TicketOffer

interface TicketsOffersInteractor {
    fun getTicketsOffers(): Resource<List<TicketOffer>>
}