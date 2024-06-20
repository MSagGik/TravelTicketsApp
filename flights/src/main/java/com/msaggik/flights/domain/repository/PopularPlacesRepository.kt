package com.msaggik.flights.domain.repository

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.model.Offer
import com.msaggik.flights.domain.model.PopularPlaces
import com.msaggik.flights.domain.model.Ticket
import com.msaggik.flights.domain.model.TicketOffer

interface PopularPlacesRepository {
    fun getPopularPlacesDomain(): List<PopularPlaces>
}