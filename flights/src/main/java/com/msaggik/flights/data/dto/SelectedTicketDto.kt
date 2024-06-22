package com.msaggik.flights.data.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticket")
data class SelectedTicketDto(
    @ColumnInfo(name = "id_server")
    val idServer: Int,
    val departure: String? = "",
    val arrival: String? = "",
    @ColumnInfo(name = "departure_time")
    val departureTime: String? = "",
    @ColumnInfo(name = "arrival_time")
    val arrivalTime: String? = "",
    @ColumnInfo(name = "number_passengers")
    val numberPassengers: Int? = 1,
    @ColumnInfo(name = "provider_name")
    val providerName: String? = "",
    val company: String? = "",
    @ColumnInfo(name = "has_transfer")
    val hasTransfer: Boolean? = false,
    @ColumnInfo(name = "arrival_airport_code")
    val arrivalAirportCode: String? = "",
    @ColumnInfo(name = "departure_airport_code")
    val departureAirportCode: String? = "",
    var badge: String? = "",
    var price: String? = ""
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
