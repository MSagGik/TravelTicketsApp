package com.msaggik.flights.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticket")
data class SelectedTicketDto(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val departure: String?,
    val arrival: String?,
    val departureTime: String?,
    val arrivalTime: String?,
    val numberPassengers: Int,
    val providerName: String?,
    val company: String?,
    val hasTransfer: Boolean?,
    val arrivalAirportCode: String?,
    val departureAirportCode: String?,
    var badge: String?,
    var price: String?
)
