package com.msaggik.flights.domain.api

import com.msaggik.flights.domain.model.SelectedTicket

interface TicketDbInteractor {

    fun isInTableTicket(id: Int, consumer: IsTicketDbConsumer)

    fun setTicket(ticket: SelectedTicket, consumer: TicketDbConsumer)

    fun getTicket(id: Int, consumer: TicketDbConsumer)

    fun getTicketsList(consumer: TicketsDbConsumer)

    fun updateTicket(ticket: SelectedTicket, consumer: TicketDbConsumer)

    fun deleteTickets(consumer: TicketsDbConsumer)

    interface IsTicketDbConsumer {
        fun consume(isTicket: Boolean)
    }

    interface TicketDbConsumer {
        fun consume(ticket: SelectedTicket)
    }

    interface TicketsDbConsumer {
        fun consume(tickets: List<SelectedTicket>)
    }
}