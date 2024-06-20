package com.msaggik.flights.data.api.network.retrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.msaggik.flights.data.api.network.NetworkClient
import com.msaggik.flights.data.dto.response.Response
import retrofit2.Retrofit

class RetrofitNetworkClient(
    private val context: Context,
    retrofit: Retrofit
) : NetworkClient {

    private val restService = retrofit.create(RestTravelTickets::class.java)

    override fun doRequestGetOffers(): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = -1 }
        }
        val response = restService.getOffers().execute()
        val body = response.body() ?: Response()
        body.resultCode = response.code()
        return body
    }

    override fun doRequestGetTicketsOffers(): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = -1 }
        }
        val response = restService.getTicketsOffers().execute()
        val body = response.body() ?: Response()
        body.resultCode = response.code()
        return body
    }

    override fun doRequestGetTickets(): Response {
        if (!isConnected()) {
            return Response().apply { resultCode = -1 }
        }
        val response = restService.getTickets().execute()
        val body = response.body() ?: Response()
        body.resultCode = response.code()
        return body
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}