package com.msaggik.flights.data.api.network.retrofit

import com.msaggik.flights.data.dto.response.entities.offers.OffersDto
import com.msaggik.flights.data.dto.response.entities.tickets.TicketsDto
import com.msaggik.flights.data.dto.response.entities.tickets_offers.TicketsOffersDto
import retrofit2.Call
import retrofit2.http.GET

internal interface RestTravelTickets {

    @GET("/v3/ad9a46ba-276c-4a81-88a6-c068e51cce3a")
    fun getOffers(): Call<OffersDto>

    @GET("/v3/38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    fun getTicketsOffers(): Call<TicketsOffersDto>

    @GET("/v3/c0464573-5a13-45c9-89f8-717436748b69")
    fun getTickets(): Call<TicketsDto>
}