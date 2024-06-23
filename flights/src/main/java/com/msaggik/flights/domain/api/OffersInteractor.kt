package com.msaggik.flights.domain.api

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.model.Offer

interface OffersInteractor {

    fun getOffers(consumer: OffersConsumer)

    interface OffersConsumer {
        fun consume(listOffer: List<Offer>?, errorMessage: String?)
    }
}