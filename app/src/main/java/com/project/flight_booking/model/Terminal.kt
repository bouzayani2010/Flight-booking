package com.project.flight_booking.model

data class Terminal (

    val airport: String,
    val timezone: String,
    val iata: String,
    val icao: String,
    val terminal: String,
    val gate: String,
    val scheduled: String,
    val estimated: String,
    val actual: String,
    val estimated_runway: String,
    val actual_runway: String,
    val delay: Int
)
