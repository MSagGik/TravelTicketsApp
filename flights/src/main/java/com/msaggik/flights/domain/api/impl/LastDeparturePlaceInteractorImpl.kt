package com.msaggik.flights.domain.api.impl

import com.msaggik.flights.domain.api.LastDeparturePlaceInteractor
import com.msaggik.flights.domain.repository.LastDeparturePlaceRepository
import java.util.concurrent.Executors

class LastDeparturePlaceInteractorImpl (
    private val repository: LastDeparturePlaceRepository
) : LastDeparturePlaceInteractor {

    val executor = Executors.newCachedThreadPool()

    override fun getLastDeparturePlace(consumer: LastDeparturePlaceInteractor.LastDeparturePlaceConsumer) {
        executor.execute {
            repository.getLastDeparturePlace()?.let { consumer.consume(it) }
        }
    }

    override fun setLastDeparturePlace(
        departurePlace: String,
        consumer: LastDeparturePlaceInteractor.LastDeparturePlaceConsumer
    ) {
        executor.execute {
            repository.setLastDeparturePlace(departurePlace)
        }
    }
}