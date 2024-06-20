package com.msaggik.common_util

import com.msaggik.common_ui.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.round

object Util {

    fun getPosterIdRes(id: Int): Int {
        return when (id) {
            1 -> R.drawable.poster_one
            2 -> R.drawable.poster_two
            3 -> R.drawable.poster_three
            else -> -1
        }
    }

    fun getFormatPrice(price: Int): String {
        return NumberFormat.getInstance(Locale.US).format(this).replace(",", " ")
    }

    fun getFormatDate(date: String): String {
        return date.substringAfter("\'T\'").substringBeforeLast(":")
    }

    fun getTravelTime(arrival: String, departure: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val startDate = format.parse(departure)
        val finishDate = format.parse(arrival)
        val deltaMillis = finishDate.time - startDate.time
        val deltaHour = deltaMillis.toDouble() / 1000 / 60 / 60
        return (round(deltaHour * 10.0) / 10.0).toString()
    }
}