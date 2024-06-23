package com.msaggik.flights.presentation.view_model.state

import com.msaggik.flights.domain.model.Ticket
import com.msaggik.flights.domain.model.TicketOffer

sealed interface TicketsOffersState {
    data object Loading : TicketsOffersState

    data class Content(
        val ticketsOffers: List<TicketOffer>
    ) : TicketsOffersState

    data class Error(
        val errorMessage: String
    ) : TicketsOffersState

    data object  Empty : TicketsOffersState
}