package com.msaggik.flights.domain.repository

import com.msaggik.flights.domain.model.SelectedTicket

interface TicketDbRepository {

    fun isInTableTicket(id: Int): Boolean

    fun addTicket(ticket: SelectedTicket)

    fun getTicket(id: Int): SelectedTicket

    fun getTickets(): List<SelectedTicket>

    fun updateTicket(ticket: SelectedTicket)

    fun deleteTicket(id: Int)

    fun deleteTickets()
}