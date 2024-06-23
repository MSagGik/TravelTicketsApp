package com.msaggik.flights.data.dto.response.entities.offers

import com.msaggik.flights.data.dto.response.Response
import com.msaggik.flights.data.dto.response.entities.PriceDto
import java.io.Serializable

data class OfferDto(
    val id: Int,
    val title: String, // route name
    val town: String, // place of arrival
    val price: PriceDto
) : Serializable
