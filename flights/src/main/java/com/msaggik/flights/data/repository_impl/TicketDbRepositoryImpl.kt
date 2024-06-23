package com.msaggik.flights.data.repository_impl

import com.msaggik.flights.data.api.db.TicketDb
import com.msaggik.flights.data.dto.SelectedTicketDto
import com.msaggik.flights.domain.model.SelectedTicket
import com.msaggik.flights.domain.repository.TicketDbRepository

class TicketDbRepositoryImpl(
    private val ticketDb: TicketDb
) : TicketDbRepository {

    override fun isInTableTicket(id: Int): Boolean {
        return ticketDb.isInTableTicket(id)
    }

    override fun addTicket(ticket: SelectedTicket) {
        ticketDb.addTicket(
            with(ticket) {
                SelectedTicketDto(
                    idServer = idServer,
                    departure = departure,
                    arrival = arrival,
                    departureTime = departureTime,
                    arrivalTime = arrivalTime,
                    numberPassengers = numberPassengers,
                    providerName = providerName,
                    company = company,
                    hasTransfer = hasTransfer,
                    arrivalAirportCode = arrivalAirportCode,
                    departureAirportCode = departureAirportCode,
                    badge = badge,
                    price = price
                )
            }
        )
    }

    override fun getTicket(id: Int): SelectedTicket {
        return with(ticketDb.getTicket(id)) {
            SelectedTicket(
                id = id,
                idServer = idServer,
                departure = departure,
                arrival = arrival,
                departureTime = departureTime,
                arrivalTime = arrivalTime,
                numberPassengers = numberPassengers,
                providerName = providerName,
                company = company,
                hasTransfer = hasTransfer,
                arrivalAirportCode = arrivalAirportCode,
                departureAirportCode = departureAirportCode,
                badge = badge,
                price = price
            )
        }
    }

    override fun getTickets(): List<SelectedTicket> {
        return ticketDb.getTickets().map {
            with(it) {
                SelectedTicket(
                    id = id,
                    idServer = idServer,
                    departure = departure,
                    arrival = arrival,
                    departureTime = departureTime,
                    arrivalTime = arrivalTime,
                    numberPassengers = numberPassengers,
                    providerName = providerName,
                    company = company,
                    hasTransfer = hasTransfer,
                    arrivalAirportCode = arrivalAirportCode,
                    departureAirportCode = departureAirportCode,
                    badge = badge,
                    price = price
                )
            }
        }
    }

    override fun updateTicket(ticket: SelectedTicket) {
        val selectedTicketDto: SelectedTicketDto = with(ticket) {
            SelectedTicketDto(
                idServer = idServer,
                departure = departure,
                arrival = arrival,
                departureTime = departureTime,
                arrivalTime = arrivalTime,
                numberPassengers = numberPassengers,
                providerName = providerName,
                company = company,
                hasTransfer = hasTransfer,
                arrivalAirportCode = arrivalAirportCode,
                departureAirportCode = departureAirportCode,
                badge = badge,
                price = price,
            )
        }
        selectedTicketDto.id = ticket.id
        ticketDb.updateTicket(selectedTicketDto)
    }

    override fun deleteTicket(id: Int) {
        ticketDb.deleteTicket(id)
    }

    override fun deleteTickets() {
        ticketDb.deleteTickets()
    }
}