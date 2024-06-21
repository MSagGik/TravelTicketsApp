package com.msaggik.flights.data.api.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.msaggik.flights.data.dto.SelectedTicketDto

@Database(entities = [(SelectedTicketDto::class)], version = 1)
abstract class TicketsDataBase() : RoomDatabase() {
    abstract fun ticketDb(): TicketDb
}

