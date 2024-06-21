package com.msaggik.flights.di

import androidx.appcompat.app.AppCompatActivity
import com.msaggik.flights.data.api.mock.PopularPlacesMock
import com.msaggik.flights.data.api.network.NetworkClient
import com.msaggik.flights.data.api.network.retrofit.RetrofitNetworkClient
import com.msaggik.flights.data.api.sp.LastDeparturePlaceSp
import com.msaggik.flights.data.api.sp.impl.LastDeparturePlaceSpImpl
import com.msaggik.flights.data.repository_impl.LastDeparturePlaceRepositoryImpl
import com.msaggik.flights.data.repository_impl.OffersRepositoryImpl
import com.msaggik.flights.data.repository_impl.PopularPlacesRepositoryImpl
import com.msaggik.flights.data.repository_impl.TicketsOffersRepositoryImpl
import com.msaggik.flights.data.repository_impl.TicketsRepositoryImpl
import com.msaggik.flights.domain.repository.LastDeparturePlaceRepository
import com.msaggik.flights.domain.repository.OffersRepository
import com.msaggik.flights.domain.repository.PopularPlacesRepository
import com.msaggik.flights.domain.repository.TicketsOffersRepository
import com.msaggik.flights.domain.repository.TicketsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val MOCKY_BASE_URL = "https://run.mocky.io"
private const val LAST_DEPARTURE_PLACE_PREFERENCES = "last_departure_place_preferences"

val dataModule = module {

    // repositories
    single<OffersRepository> {
        OffersRepositoryImpl(
            androidContext(),
            networkClient = get()
        )
    }

    single<TicketsRepository> {
        TicketsRepositoryImpl(
            androidContext(),
            networkClient = get()
        )
    }

    single<TicketsOffersRepository> {
        TicketsOffersRepositoryImpl(
            androidContext(),
            networkClient = get()
        )
    }

    single<PopularPlacesRepository> {
        PopularPlacesRepositoryImpl(
            mockPopularPlaces = get()
        )
    }

    single<LastDeparturePlaceRepository> {
        LastDeparturePlaceRepositoryImpl(
            lastDeparturePlaceSp = get()
        )
    }

    // network
    single<NetworkClient> {
        RetrofitNetworkClient(
            androidContext(),
            retrofit = get()
        )
    }

    single {
        Retrofit.Builder()
            .baseUrl(MOCKY_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // mock
    single {
        PopularPlacesMock(
            androidContext()
        )
    }

    // sp
    single<LastDeparturePlaceSp> {
        LastDeparturePlaceSpImpl(
            sharedPreferences = get()
        )
    }

    single {
        androidContext()
            .getSharedPreferences(LAST_DEPARTURE_PLACE_PREFERENCES, AppCompatActivity.MODE_PRIVATE)
    }
}