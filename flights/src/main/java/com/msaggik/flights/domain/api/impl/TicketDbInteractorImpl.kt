package com.msaggik.flights.domain.api.impl

import com.msaggik.flights.domain.api.TicketDbInteractor
import com.msaggik.flights.domain.model.SelectedTicket
import com.msaggik.flights.domain.repository.TicketDbRepository
import java.util.concurrent.Executors

class TicketDbInteractorImpl (
    private val repository: TicketDbRepository
) : TicketDbInteractor {

    val executor = Executors.newCachedThreadPool()

    override fun isInTableTicket(
        id: Int,
        consumer: TicketDbInteractor.IsTicketDbConsumer
    ) {
        executor.execute{
            consumer.consume(repository.isInTableTicket(id))
        }
    }

    override fun setTicket(
        ticket: SelectedTicket,
        consumer: TicketDbInteractor.TicketDbConsumer
    ) {
        executor.execute {
            repository.addTicket(ticket)
        }
    }

    override fun getTicket(
        id: Int,
        consumer: TicketDbInteractor.TicketDbConsumer
    ) {
        executor.execute {
            consumer.consume(repository.getTicket(id))
        }
    }

    override fun getTicketsList(
        consumer: TicketDbInteractor.TicketsDbConsumer
    ) {
        executor.execute {
            consumer.consume(repository.getTickets())
        }
    }

    override fun updateTicket(
        ticket: SelectedTicket,
        consumer: TicketDbInteractor.TicketDbConsumer
    ) {
        repository.updateTicket(ticket)
    }

    override fun deleteTicket(
        id: Int,
        consumer: TicketDbInteractor.TicketDbConsumer
    ) {
        repository.deleteTicket(id)
    }

    override fun deleteTickets(
        consumer: TicketDbInteractor.TicketsDbConsumer
    ) {
        repository.deleteTickets()
    }
}