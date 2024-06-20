package com.msaggik.flights.data.repository_impl

import android.content.Context
import com.msaggik.common_ui.R
import com.msaggik.common_util.Resource
import com.msaggik.common_util.Utils
import com.msaggik.flights.data.api.network.NetworkClient
import com.msaggik.flights.data.dto.response.entities.tickets.TicketsDto
import com.msaggik.flights.domain.model.Ticket
import com.msaggik.flights.domain.repository.TicketsRepository

class TicketsRepositoryImpl (
    private val context: Context,
    private val networkClient: NetworkClient,
) : TicketsRepository {
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
                        arrivalTime = Utils.getFormatDate(arrival.date),
                        departureTime = Utils.getFormatDate(departure.date),
                        travelTime = Utils.getTravelTime(arrival.date, departure.date),
                        badge = badge,
                        hasTransfer = hasTransfer,
                        price = Utils.getFormatPrice(price.value)
                    )
                }
            })
            else -> Resource.Error(context.getString(R.string.error))
        }
    }
}