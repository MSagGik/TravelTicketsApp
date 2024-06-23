package com.msaggik.flights.di

import com.msaggik.flights.domain.api.LastDeparturePlaceInteractor
import com.msaggik.flights.domain.api.OffersInteractor
import com.msaggik.flights.domain.api.PopularPlacesInteractor
import com.msaggik.flights.domain.api.TicketDbInteractor
import com.msaggik.flights.domain.api.TicketsInteractor
import com.msaggik.flights.domain.api.TicketsOffersInteractor
import com.msaggik.flights.domain.api.impl.LastDeparturePlaceInteractorImpl
import com.msaggik.flights.domain.api.impl.OffersInteractorImpl
import com.msaggik.flights.domain.api.impl.PopularPlacesInteractorImpl
import com.msaggik.flights.domain.api.impl.TicketDbInteractorImpl
import com.msaggik.flights.domain.api.impl.TicketsInteractorImpl
import com.msaggik.flights.domain.api.impl.TicketsOffersInteractorImpl
import org.koin.dsl.module

val domainModule = module {

    single<OffersInteractor> {
        OffersInteractorImpl(
            repository = get()
        )
    }

    single<PopularPlacesInteractor> {
        PopularPlacesInteractorImpl(
            repository = get()
        )
    }

    single<TicketsOffersInteractor> {
        TicketsOffersInteractorImpl(
            repository = get()
        )
    }

    single<TicketsInteractor> {
        TicketsInteractorImpl(
            repository = get()
        )
    }

    single<LastDeparturePlaceInteractor> {
        LastDeparturePlaceInteractorImpl(
            repository = get()
        )
    }

    single<TicketDbInteractor> {
        TicketDbInteractorImpl(
            repository = get()
        )
    }
}