package com.msaggik.flights.di

import com.msaggik.flights.presentation.view_model.LastDeparturePlaceViewModel
import com.msaggik.flights.presentation.view_model.OffersViewModel
import com.msaggik.flights.presentation.view_model.PopularPlacesViewModel
import com.msaggik.flights.presentation.view_model.TicketDbViewModel
import com.msaggik.flights.presentation.view_model.TicketsOffersViewModel
import com.msaggik.flights.presentation.view_model.TicketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        OffersViewModel(
            offersInteractor = get()
        )
    }

    viewModel {
        PopularPlacesViewModel(
            popularPlacesInteractor = get()
        )
    }

    viewModel {
        TicketsOffersViewModel(
            ticketsOffersInteractor = get()
        )
    }

    viewModel {
        TicketsViewModel(
            ticketsInteractor = get()
        )
    }

    viewModel {
        LastDeparturePlaceViewModel(
            lastDeparturePlaceInteractor = get()
        )
    }

    viewModel {
        TicketDbViewModel(
            ticketDbInteractor = get()
        )
    }
}
