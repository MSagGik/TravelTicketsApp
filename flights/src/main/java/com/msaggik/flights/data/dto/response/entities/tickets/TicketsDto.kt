package com.msaggik.flights.data.dto.response.entities.tickets

import com.msaggik.flights.data.dto.response.Response

data class TicketsDto(
    val tickets: List<TicketDto>
) : Response()
