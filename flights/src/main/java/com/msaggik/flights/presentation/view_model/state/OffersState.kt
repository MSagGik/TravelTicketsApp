package com.msaggik.flights.presentation.view_model.state

import com.msaggik.flights.domain.model.Offer

sealed interface OffersState {

    data object Loading : OffersState

    data class Content(
        val offers: List<Offer>
    ) : OffersState

    data class Error(
        val errorMessage: String
    ) : OffersState

    data object  Empty : OffersState
}