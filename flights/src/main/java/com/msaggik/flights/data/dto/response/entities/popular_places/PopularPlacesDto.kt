package com.msaggik.flights.data.dto.response.entities.popular_places

import java.io.Serializable

data class PopularPlacesDto(
    val id: Int,
    val town: String,
    val description: String,
    val posterIdRes: Int
) : Serializable
