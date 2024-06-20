package com.msaggik.flights.data.dto.response.entities.tickets

import com.google.gson.annotations.SerializedName
import com.msaggik.flights.data.dto.response.entities.tickets.child.ArrivalDto
import com.msaggik.flights.data.dto.response.entities.tickets.child.DepartureDto
import com.msaggik.flights.data.dto.response.entities.tickets.child.HandLuggageDto
import com.msaggik.flights.data.dto.response.entities.tickets.child.LuggageDto
import com.msaggik.flights.data.dto.response.entities.PriceDto
import java.io.Serializable

data class TicketDto(
    var id: Int,
    var badge: String,
    var price: PriceDto,
    @SerializedName("provider_name") var providerName: String,
    var company: String,
    var departure: DepartureDto,
    var arrival: ArrivalDto,
    @SerializedName("has_transfer") var hasTransfer: Boolean,
    @SerializedName("has_visa_transfer") var hasVisaTransfer: Boolean,
    @SerializedName("luggage") var luggage: LuggageDto,
    @SerializedName("hand_luggage") var handLuggage: HandLuggageDto,
    @SerializedName("is_returnable") var isReturnable: Boolean,
    @SerializedName("is_exchangable") var isExchangable: Boolean
) : Serializable
