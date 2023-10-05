package com.project.flight_booking.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.flight_booking.model.Trip
import com.project.flight_booking.repositry.MainRepositry
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepositry) : ViewModel() {
        private val _data = MutableLiveData<Trip>()
        val data: LiveData<Trip>
            get() = _data

    fun fetchData() {
        viewModelScope.launch {
            try {
                // Perform the asynchronous operation, e.g., network request
                val result = repository.loadData()

                // Update LiveData with the result on the main thread
                _data.value = result
            } catch (exception: Exception) {
                // Handle exceptions or errors here
                // You can also update LiveData with an error state if needed
            }
        }
    }

    // Cancel any ongoing coroutines when the ViewModel is cleared
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }



}

