package com.msaggik.flights.data.repository_impl

import android.content.Context
import com.msaggik.common_ui.R
import com.msaggik.common_util.Resource
import com.msaggik.common_util.Util
import com.msaggik.flights.data.api.db.PopularPlacesDataBase
import com.msaggik.flights.data.api.network.NetworkClient
import com.msaggik.flights.data.dto.response.entities.offers.OffersDto
import com.msaggik.flights.data.dto.response.entities.tickets.TicketsDto
import com.msaggik.flights.data.dto.response.entities.tickets_offers.TicketsOffersDto
import com.msaggik.flights.domain.model.Offer
import com.msaggik.flights.domain.model.PopularPlaces
import com.msaggik.flights.domain.model.Ticket
import com.msaggik.flights.domain.model.TicketOffer
import com.msaggik.flights.domain.repository.TicketRepository

class TicketRepositoryImpl (
    private val context: Context,
    private val networkClient: NetworkClient,
    private val mockPopularPlaces: PopularPlacesDataBase,
) : TicketRepository {
    override fun getPopularPlaces(): List<PopularPlaces> {
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

    override fun getOffersDomain(): Resource<List<Offer>> {
        val response = networkClient.doRequestGetOffers()
        return when(response.resultCode) {
            -1 -> Resource.Error(context.getString(R.string.no_connection))
            200 -> Resource.Success((response as OffersDto).offers.map {
                with(it) {
                    Offer (
                        id = id,
                        title = title,
                        town = town,
                        price = Util.getFormatPrice(price.value),
                        posterIdRes = Util.getPosterIdRes(id)
                    )
                }
            })
            else -> Resource.Error(context.getString(R.string.error))
        }
    }

    override fun getTicketsOffersDomain(): Resource<List<TicketOffer>> {
        val response = networkClient.doRequestGetTicketsOffers()
        return when(response.resultCode) {
            -1 -> Resource.Error(context.getString(R.string.no_connection))
            200 -> Resource.Success((response as TicketsOffersDto).ticketsOffers.map {
                with(it) {
                    TicketOffer(
                        id = id,
                        title = title,
                        timeRange = timeRange.joinToString(" "),
                        price = Util.getFormatPrice(price.value)
                    )
                }
            })
            else -> Resource.Error(context.getString(R.string.error))
        }
    }

    override fun getTicketsDomain(): Resource<List<Ticket>> {
        val response = networkClient.doRequestGetTickets()
        return when(response.resultCode) {
            -1 -> Resource.Error(context.getString(R.string.no_connection))
            200 -> Resource.Success((response as TicketsDto).tickets.map {
                with(it) {
                    Ticket(
                        id = id,
                        arrivalAirportCode = departure.airport,
                        departureAirportCode = arrival.airport,
                        arrivalTime = Util.getFormatDate(arrival.date),
                        departureTime = Util.getFormatDate(departure.date),
                        travelTime = Util.getTravelTime(arrival.date, departure.date),
                        badge = badge,
                        hasTransfer = hasTransfer,
                        price = Util.getFormatPrice(price.value)
                    )
                }
            })
            else -> Resource.Error(context.getString(R.string.error))
        }
    }
}