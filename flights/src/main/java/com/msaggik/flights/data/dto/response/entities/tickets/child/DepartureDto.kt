package com.msaggik.flights.data.dto.response.entities.tickets.child

import java.io.Serializable

data class DepartureDto(
    var town: String,
    var date: String,
    var airport: String
) : Serializable
