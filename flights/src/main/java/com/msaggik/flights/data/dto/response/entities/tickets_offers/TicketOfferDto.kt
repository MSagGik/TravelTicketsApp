package com.msaggik.flights.data.dto.response.entities.tickets_offers

import com.google.gson.annotations.SerializedName
import com.msaggik.flights.data.dto.response.entities.PriceDto
import java.io.Serializable

data class TicketOfferDto(
    val id: Int,
    val title: String,
    @SerializedName("time_range") val timeRange: List<String>,
    val price: PriceDto
) : Serializable
