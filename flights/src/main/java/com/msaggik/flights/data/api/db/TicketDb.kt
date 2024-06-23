package com.msaggik.flights.data.api.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.msaggik.flights.data.dto.SelectedTicketDto

@Dao
interface TicketDb {

    @Query("SELECT EXISTS(SELECT * FROM ticket WHERE id=:id)")
    fun isInTableTicket(id: Int): Boolean

    @Insert
    fun addTicket(ticket: SelectedTicketDto)

    @Query("SELECT * FROM ticket WHERE id=:id")
    fun getTicket(id: Int): SelectedTicketDto

    @Query("SELECT * FROM ticket")
    fun getTickets(): List<SelectedTicketDto>

    @Update
    fun updateTicket(ticket: SelectedTicketDto)

    @Query("DELETE FROM ticket WHERE id=:id")
    fun deleteTicket(id: Int)

    @Query("DELETE FROM ticket")
    fun deleteTickets()
}