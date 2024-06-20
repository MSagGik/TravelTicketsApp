package com.msaggik.flights.data.dto.response.entities.tickets.child

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HandLuggageDto(
    @SerializedName("has_hand_luggage") var hasHandLuggage: Boolean,
    var size: String
) : Serializable
