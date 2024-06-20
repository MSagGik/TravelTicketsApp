package com.msaggik.flights.data.dto.response.entities.tickets.child

import com.google.gson.annotations.SerializedName
import com.msaggik.flights.data.dto.response.entities.PriceDto
import java.io.Serializable

data class LuggageDto(
    @SerializedName("has_luggage") var hasLuggage: Boolean,
    var price: PriceDto?
) : Serializable
