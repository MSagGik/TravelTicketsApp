package com.msaggik.flights.domain.api

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.model.Ticket

interface TicketsInteractor {
    fun getTickets(): Resource<List<Ticket>>
}