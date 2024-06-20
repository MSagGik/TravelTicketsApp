package com.msaggik.flights.domain.repository

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.model.Ticket

interface TicketsRepository {
    fun getTicketsDomain(): Resource<List<Ticket>>
}