package com.msaggik.flights.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msaggik.flights.domain.api.TicketsInteractor
import com.msaggik.flights.domain.api.TicketsOffersInteractor
import com.msaggik.flights.domain.model.Ticket
import com.msaggik.flights.domain.model.TicketOffer
import com.msaggik.flights.presentation.view_model.state.TicketsOffersState
import com.msaggik.flights.presentation.view_model.state.TicketsState

class TicketsOffersViewModel (
    private val ticketsOffersInteractor: TicketsOffersInteractor
) : ViewModel() {

    init {
        readTicketsOffers()
    }

    private val stateLiveData = MutableLiveData<TicketsOffersState>()
    fun getStateLiveData(): LiveData<TicketsOffersState> = mediatorStateLiveData

    private val mediatorStateLiveData = MediatorLiveData<TicketsOffersState>().also { liveData ->
        liveData.addSource(stateLiveData) { state ->
            liveData.value = when (state) {
                is TicketsOffersState.Content -> TicketsOffersState.Content(state.ticketsOffers)
                is TicketsOffersState.Empty -> state
                is TicketsOffersState.Error -> state
                is TicketsOffersState.Loading -> state
            }
        }
    }

    private fun readTicketsOffers() {
        ticketsOffersInteractor.getTicketsOffers(object : TicketsOffersInteractor.TicketsOffersConsumer {
            override fun consume(listTicketsOffers: List<TicketOffer>?, errorMessage: String?) {
                val ticketsOffers = mutableListOf<TicketOffer>()
                if (listTicketsOffers != null) {
                    ticketsOffers.addAll(listTicketsOffers)
                }
                when {
                    errorMessage != null -> {
                        renderState(
                            TicketsOffersState.Error(
                                errorMessage = errorMessage,
                            )
                        )
                    }

                    ticketsOffers.isEmpty() -> {
                        renderState(
                            TicketsOffersState.Empty
                        )
                    }

                    else -> {
                        renderState(
                            TicketsOffersState.Content(
                                ticketsOffers = ticketsOffers
                            )
                        )
                    }
                }
            }
        })
    }

    private fun renderState(state: TicketsOffersState) {
        stateLiveData.postValue(state)
    }
}