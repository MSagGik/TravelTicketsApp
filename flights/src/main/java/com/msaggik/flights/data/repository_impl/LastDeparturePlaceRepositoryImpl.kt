package com.msaggik.flights.data.repository_impl

import com.msaggik.flights.data.api.sp.LastDeparturePlaceSp
import com.msaggik.flights.domain.repository.LastDeparturePlaceRepository

class LastDeparturePlaceRepositoryImpl(
    private val lastDeparturePlaceSp: LastDeparturePlaceSp
) : LastDeparturePlaceRepository {
    override fun getLastDeparturePlace(): String? {
        return lastDeparturePlaceSp.getLastDeparturePlace()
    }

    override fun setLastDeparturePlace(departurePlace: String) {
        lastDeparturePlaceSp.setLastDeparturePlace(departurePlace)
    }
}