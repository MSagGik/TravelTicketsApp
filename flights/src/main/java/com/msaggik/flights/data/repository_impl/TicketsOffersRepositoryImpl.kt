package com.msaggik.flights.data.repository_impl

import android.content.Context
import com.msaggik.common_ui.R
import com.msaggik.common_util.Resource
import com.msaggik.common_util.Util
import com.msaggik.flights.data.api.network.NetworkClient
import com.msaggik.flights.data.dto.response.entities.tickets_offers.TicketsOffersDto
import com.msaggik.flights.domain.model.TicketOffer
import com.msaggik.flights.domain.repository.TicketsOffersRepository

class TicketsOffersRepositoryImpl (
    private val context: Context,
    private val networkClient: NetworkClient
) : TicketsOffersRepository{

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
}