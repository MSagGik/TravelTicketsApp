package com.msaggik.flights.data.repository_impl

import com.msaggik.flights.data.api.db.PopularPlacesDataBase
import com.msaggik.flights.domain.model.PopularPlaces
import com.msaggik.flights.domain.repository.PopularPlacesRepository

class PopularPlacesRepositoryImpl (
    private val mockPopularPlaces: PopularPlacesDataBase,
) : PopularPlacesRepository {
    override fun getPopularPlacesDomain(): List<PopularPlaces> {
        return mockPopularPlaces.getPopularPlacesDataBase().map {
            with(it) {
                PopularPlaces(
                    id = id,
                    town = town,
                    description = description,
                    posterIdRes = posterIdRes
                )
            }
        }
    }
}