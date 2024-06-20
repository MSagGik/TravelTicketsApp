package com.msaggik.flights.domain.api

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.model.Offer
import com.msaggik.flights.domain.model.Ticket

interface TicketsInteractor {

    fun getTickets(consumer: TicketsConsumer)

    interface TicketsConsumer {
        fun consume(listTicket: List<Ticket>?, errorMessage: String?)
    }
}