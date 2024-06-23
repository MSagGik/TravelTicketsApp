package com.msaggik.flights.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msaggik.flights.domain.api.TicketsInteractor
import com.msaggik.flights.domain.model.Ticket
import com.msaggik.flights.presentation.view_model.state.TicketsState

class TicketsViewModel (
    private val ticketsInteractor: TicketsInteractor
) : ViewModel() {

    init {
        readTickets()
    }

    private val stateLiveData = MutableLiveData<TicketsState>()
    fun getStateLiveData(): LiveData<TicketsState> = mediatorStateLiveData

    private val mediatorStateLiveData = MediatorLiveData<TicketsState>().also { liveData ->
        liveData.addSource(stateLiveData) { state ->
            liveData.value = when (state) {
                is TicketsState.Content -> TicketsState.Content(state.tickets)
                is TicketsState.Empty -> state
                is TicketsState.Error -> state
                is TicketsState.Loading -> state
            }
        }
    }

    private fun readTickets() {
        ticketsInteractor.getTickets(object : TicketsInteractor.TicketsConsumer {
            override fun consume(listTicket: List<Ticket>?, errorMessage: String?) {
                val tickets = mutableListOf<Ticket>()
                if (listTicket != null) {
                    tickets.addAll(listTicket)
                }
                when {
                    errorMessage != null -> {
                        renderState(
                            TicketsState.Error(
                                errorMessage = errorMessage,
                            )
                        )
                    }

                    tickets.isEmpty() -> {
                        renderState(
                            TicketsState.Empty
                        )
                    }

                    else -> {
                        renderState(
                            TicketsState.Content(
                                tickets = tickets
                            )
                        )
                    }
                }
            }
        })
    }

    private fun renderState(state: TicketsState) {
        stateLiveData.postValue(state)
    }
}