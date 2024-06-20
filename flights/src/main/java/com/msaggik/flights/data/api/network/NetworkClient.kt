package com.msaggik.flights.data.api.network

import com.msaggik.flights.data.dto.response.Response

interface NetworkClient {
    fun doRequestGetOffers(): Response
    fun doRequestGetTicketsOffers(): Response
    fun doRequestGetTickets(): Response
}