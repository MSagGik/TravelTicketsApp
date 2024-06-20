package com.msaggik.flights.domain.repository

import com.msaggik.flights.domain.model.Offer
import com.msaggik.flights.domain.model.PopularPlaces
import com.msaggik.flights.domain.model.Ticket
import com.msaggik.flights.domain.model.TicketOffer

interface TicketRepository {
    fun getPopularPlaces(): List<PopularPlaces>
    fun getOffersDomain(): Resource<List<Offer>>
    fun getTicketsOffersDomain(): Resource<List<TicketOffer>>
    fun getTicketsDomain(): Resource<List<Ticket>>
}