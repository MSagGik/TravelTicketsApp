package com.msaggik.flights.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msaggik.flights.domain.api.TicketDbInteractor
import com.msaggik.flights.domain.model.SelectedTicket

private const val DEFAULT_ID_SEALED_TICKED = 1
class TicketDbViewModel (
    private val ticketDbInteractor: TicketDbInteractor
) : ViewModel() {

    init {
        initFromDbLiveData()
    }

    private val selectedTicketLiveData = MutableLiveData<SelectedTicket>()
    fun getSelectedTicketLiveData(): LiveData<SelectedTicket> = selectedTicketLiveData

    private val selectedTicketsLiveData = MutableLiveData<List<SelectedTicket>>()
    fun getSelectedTicketsLiveData(): LiveData<List<SelectedTicket>> = selectedTicketsLiveData

    private fun initFromDbLiveData() {
        ticketDbInteractor.getTicket(DEFAULT_ID_SEALED_TICKED, object : TicketDbInteractor.TicketDbConsumer {
            override fun consume(ticket: SelectedTicket) {
                selectedTicketLiveData.postValue(ticket)
            }
        })

        ticketDbInteractor.getTicketsList(object : TicketDbInteractor.TicketsDbConsumer {
            override fun consume(tickets: List<SelectedTicket>) {
                selectedTicketsLiveData.postValue(tickets)
            }
        })
    }

    fun setTicketLiveData(ticket: SelectedTicket) {
        ticketDbInteractor.setTicket(ticket, object : TicketDbInteractor.TicketDbConsumer {
            override fun consume(ticket: SelectedTicket) {
                selectedTicketLiveData.postValue(ticket)
            }
        })
    }

    fun updateTicketLiveData(ticket: SelectedTicket) {
        ticketDbInteractor.updateTicket(ticket, object : TicketDbInteractor.TicketDbConsumer {
            override fun consume(ticket: SelectedTicket) {
                selectedTicketLiveData.postValue(ticket)
            }
        })
    }

    fun removeTicketLiveData() {
        ticketDbInteractor.deleteTickets(object : TicketDbInteractor.TicketsDbConsumer {
            override fun consume(tickets: List<SelectedTicket>) {
                selectedTicketsLiveData.postValue(tickets)
            }
        })
    }
}