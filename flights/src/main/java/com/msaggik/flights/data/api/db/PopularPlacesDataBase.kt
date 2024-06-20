package com.msaggik.flights.data.api.db

import android.content.Context
import com.msaggik.common_ui.R
import com.msaggik.flights.data.dto.response.entities.popular_places.PopularPlacesDto

class PopularPlacesDataBase(
    private val context: Context
) {
    fun getPopularPlacesDataBase() : List<PopularPlacesDto> {
        return listOf(
            PopularPlacesDto(
                id = 1,
                town = context.getString(R.string.istanbul_city),
                description = context.getString(R.string.popular_destination),
                posterIdRes = R.drawable.image_place_one
            ),
            PopularPlacesDto(
                id = 2,
                town = context.getString(R.string.sochi_city),
                description = context.getString(R.string.popular_destination),
                posterIdRes = R.drawable.image_place_two
            ),
            PopularPlacesDto(
                id = 3,
                town = context.getString(R.string.phuket_city),
                description = context.getString(R.string.popular_destination),
                posterIdRes = R.drawable.image_place_three
            )
        )
    }
}