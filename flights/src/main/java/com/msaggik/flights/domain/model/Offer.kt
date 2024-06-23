package com.msaggik.flights.domain.model

import com.msaggik.flights.data.dto.response.entities.PriceDto

data class Offer(
    val id: Int,
    val title: String, // route name
    val town: String, // place of arrival
    val price: String,
    val posterIdRes: Int
)
