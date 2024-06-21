package com.msaggik.flights.domain.api

import com.msaggik.flights.domain.model.SelectedTicket

interface TicketDbInteractor {

    fun setTicket(ticket: SelectedTicket, consumer: TicketDbConsumer)

    fun getTicket(id: Int, consumer: TicketDbConsumer)

    fun getTicketsList(consumer: TicketsDbConsumer)

    fun updateTicket(ticket: SelectedTicket, consumer: TicketDbConsumer)

    fun deleteTickets(consumer: TicketsDbConsumer)

    interface TicketDbConsumer {
        fun consume(ticket: SelectedTicket)
    }

    interface TicketsDbConsumer {
        fun consume(tickets: List<SelectedTicket>)
    }
}