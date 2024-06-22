package com.msaggik.flights.data.api.sp.impl

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.msaggik.flights.data.api.sp.LastDeparturePlaceSp

private const val LAST_DEPARTURE_PLACE_KEY = "last_departure_place_key"
class LastDeparturePlaceSpImpl(
    private val sharedPreferences: SharedPreferences
) : LastDeparturePlaceSp{

    override fun getLastDeparturePlace(): String? {
        return sharedPreferences.getString(LAST_DEPARTURE_PLACE_KEY, null)
    }

    override fun setLastDeparturePlace(departurePlace: String) {
        sharedPreferences.edit {
            putString(LAST_DEPARTURE_PLACE_KEY, departurePlace)
        }
    }
}