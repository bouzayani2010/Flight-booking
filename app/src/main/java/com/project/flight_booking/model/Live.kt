package com.project.flight_booking.model
data class Live (

    val updated: String,
    val latitude: Float,
    val longitude: Float,
    val altitude: Float,
    val direction: Float,
    val speed_horizontal: Float,
    val speed_vertical: Float,
    val is_ground: Boolean
)
