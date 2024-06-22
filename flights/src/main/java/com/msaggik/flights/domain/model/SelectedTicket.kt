package com.msaggik.flights.domain.model

data class SelectedTicket(
    val id: Int,
    val departure: String? = "",
    val arrival: String? = "",
    val departureTime: String? = "",
    val arrivalTime: String? = "",
    val numberPassengers: Int? = 1,
    val providerName: String? = "",
    val company: String? = "",
    val hasTransfer: Boolean? = false,
    val arrivalAirportCode: String? = "",
    val departureAirportCode: String? = "",
    var badge: String? = "",
    var price: String? = ""
)
