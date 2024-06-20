package com.msaggik.flights.domain.api.impl

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.api.TicketsInteractor
import com.msaggik.flights.domain.repository.TicketsRepository
import java.util.concurrent.Executors

class TicketsInteractorImpl(
    private val repository: TicketsRepository
) : TicketsInteractor {

    override fun getTickets(consumer: TicketsInteractor.TicketsConsumer) {
        Executors.newCachedThreadPool().execute {
            when (val resource = repository.getTicketsDomain()) {
                is Resource.Success -> { consumer.consume(resource.data, null)}
                is Resource.Error -> { consumer.consume(null, resource.message)}
            }
        }
    }
}