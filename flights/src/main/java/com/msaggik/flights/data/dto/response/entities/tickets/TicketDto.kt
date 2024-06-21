package com.msaggik.flights.data.dto.response.entities.tickets

import com.google.gson.annotations.SerializedName
import com.msaggik.flights.data.dto.response.entities.tickets.child.ArrivalDto
import com.msaggik.flights.data.dto.response.entities.tickets.child.DepartureDto
import com.msaggik.flights.data.dto.response.entities.tickets.child.HandLuggageDto
import com.msaggik.flights.data.dto.response.entities.tickets.child.LuggageDto
import com.msaggik.flights.data.dto.response.entities.PriceDto
import java.io.Serializable

data class TicketDto(
    val id: Int,
    val badge: String,
    val price: PriceDto,
    @SerializedName("provider_name") val providerName: String,
    val company: String,
    val departure: DepartureDto,
    val arrival: ArrivalDto,
    @SerializedName("has_transfer") val hasTransfer: Boolean,
    @SerializedName("has_visa_transfer") val hasVisaTransfer: Boolean,
    @SerializedName("luggage") val luggage: LuggageDto,
    @SerializedName("hand_luggage") val handLuggage: HandLuggageDto,
    @SerializedName("is_returnable") val isReturnable: Boolean,
    @SerializedName("is_exchangable") val isExchangable: Boolean
) : Serializable
