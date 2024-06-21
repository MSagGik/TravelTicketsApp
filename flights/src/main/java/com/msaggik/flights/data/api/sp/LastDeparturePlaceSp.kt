package com.msaggik.flights.data.api.sp

interface LastDeparturePlaceSp {
    fun getLastDeparturePlace(): String?
    fun setLastDeparturePlace(departurePlace: String)
}