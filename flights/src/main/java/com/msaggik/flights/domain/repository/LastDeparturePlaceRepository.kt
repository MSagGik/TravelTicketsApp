package com.msaggik.flights.domain.repository

interface LastDeparturePlaceRepository {
    fun getLastDeparturePlace(): String?
    fun setLastDeparturePlace(departurePlace: String)
}