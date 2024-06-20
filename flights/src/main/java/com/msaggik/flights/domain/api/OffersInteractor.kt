package com.msaggik.flights.domain.api

import com.msaggik.common_util.Resource
import com.msaggik.flights.domain.model.Offer

interface OffersInteractor {
    fun getOffers(): Resource<List<Offer>>
}