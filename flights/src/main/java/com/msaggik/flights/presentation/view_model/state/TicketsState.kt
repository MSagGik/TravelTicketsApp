package com.msaggik.flights.presentation.view_model.state

import com.msaggik.flights.domain.model.Ticket

sealed interface TicketsState {

    data object Loading : TicketsState

    data class Content(
        val tickets: List<Ticket>
    ) : TicketsState

    data class Error(
        val errorMessage: String
    ) : TicketsState

    data object  Empty : TicketsState
}