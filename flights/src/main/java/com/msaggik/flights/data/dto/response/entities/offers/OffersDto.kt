package com.msaggik.flights.data.dto.response.entities.offers

import com.msaggik.flights.data.dto.response.Response

data class OffersDto(
    val offers: List<OfferDto>
) : Response()
