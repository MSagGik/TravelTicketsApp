package com.msaggik.flights.domain.api

interface LastDeparturePlaceInteractor {

    fun getLastDeparturePlace(consumer: LastDeparturePlaceConsumer)

    fun setLastDeparturePlace(departurePlace: String, consumer: LastDeparturePlaceConsumer)

    interface LastDeparturePlaceConsumer {
        fun consume(departurePlace: String)
    }
}