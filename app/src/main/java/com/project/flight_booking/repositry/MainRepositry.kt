package com.project.flight_booking.repositry

import com.project.flight_booking.apis.ApiService
import com.project.flight_booking.model.Trip

class MainRepositry(private val apiService: ApiService) {
    suspend fun loadData(): Trip {
        return apiService.getData()
    }
}


