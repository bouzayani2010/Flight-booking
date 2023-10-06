package com.project.flight_booking.model

data class Trip(
 val flight_date: String,
 val flight_status:String,
 val departure:Terminal,
 val arrival:Terminal,
 val airline: Airline,
 val flight:Flight,
 val aircraft: Aircraft,
 val live:Live
)