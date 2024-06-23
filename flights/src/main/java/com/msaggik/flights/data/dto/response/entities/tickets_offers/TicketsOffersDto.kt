package com.msaggik.flights.data.dto.response.entities.tickets_offers

import com.google.gson.annotations.SerializedName
import com.msaggik.flights.data.dto.response.Response

data class TicketsOffersDto(
    @SerializedName("tickets_offers") val ticketsOffers: List<TicketOfferDto>
) : Response()
