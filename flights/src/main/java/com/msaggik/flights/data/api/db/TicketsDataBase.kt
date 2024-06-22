package com.msaggik.flights.data.api.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.msaggik.flights.data.dto.SelectedTicketDto
private const val VERSION_DATA_BASE = 1
@Database(entities = [(SelectedTicketDto::class)], version = VERSION_DATA_BASE)
abstract class TicketsDataBase() : RoomDatabase() {
    abstract fun ticketDb(): TicketDb
}

