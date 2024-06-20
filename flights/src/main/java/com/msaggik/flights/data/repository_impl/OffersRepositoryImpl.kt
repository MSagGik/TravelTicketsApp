package com.msaggik.flights.data.repository_impl

import android.content.Context
import com.msaggik.common_ui.R
import com.msaggik.common_util.Resource
import com.msaggik.common_util.Util
import com.msaggik.flights.data.api.network.NetworkClient
import com.msaggik.flights.data.dto.response.entities.offers.OffersDto
import com.msaggik.flights.domain.model.Offer
import com.msaggik.flights.domain.repository.OffersRepository

class OffersRepositoryImpl (
    private val context: Context,
    private val networkClient: NetworkClient
) : OffersRepository {

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
}