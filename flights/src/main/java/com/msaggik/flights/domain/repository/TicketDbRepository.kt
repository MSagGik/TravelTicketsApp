package com.msaggik.flights.domain.repository

import com.msaggik.flights.domain.model.SelectedTicket

interface TicketDbRepository {

    fun addTicket(ticket: SelectedTicket)

    fun getTicket(id: Int): SelectedTicket

    fun getTickets(): List<SelectedTicket>

    fun updateTicket(ticket: SelectedTicket)

    fun deleteTickets()
}