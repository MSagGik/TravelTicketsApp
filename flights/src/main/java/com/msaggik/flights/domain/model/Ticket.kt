package com.msaggik.flights.domain.model

data class Ticket(
    var id: Int,
    val arrivalAirportCode: String,
    val departureAirportCode: String,
    val arrivalTime: String,
    val departureTime: String,
    val travelTime: String,
    var badge: String?,
    val hasTransfer: Boolean,
    var price: String,
)
