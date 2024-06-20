package com.msaggik.flights.domain.api.impl

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.api.TicketsInteractor
import com.msaggik.flights.domain.model.Ticket
import com.msaggik.flights.domain.repository.TicketsRepository

class TicketsInteractorImpl(
    private val repository: TicketsRepository
) : TicketsInteractor {
    override fun getTickets(): Resource<List<Ticket>> {
        return repository.getTicketsDomain()
    }
}