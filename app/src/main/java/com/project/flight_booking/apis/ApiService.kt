package com.project.flight_booking.apis

import com.project.flight_booking.model.Trip
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("vols")
    fun getTrips(): Call<List<Trip>>
}
