package com.project.flight_booking.repositry

import com.project.flight_booking.apis.ApiService
import com.project.flight_booking.model.Trip
import retrofit2.Call

class MainRepositry(private val apiService: ApiService) {
    suspend fun loadData(): Call<List<Trip>> {
        return apiService.getTrips()
    }
}


