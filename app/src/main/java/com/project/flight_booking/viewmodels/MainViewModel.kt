package com.project.flight_booking.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.flight_booking.model.Trip
import com.project.flight_booking.repositry.MainRepositry
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: MainRepositry) : ViewModel() {
        private val _data = MutableLiveData<List<Trip>>()
        val data: LiveData<List<Trip>>
            get() = _data

    fun fetchData() {
        viewModelScope.launch {
            repository.loadData().enqueue(object :Callback<List<Trip>>{
                override fun onResponse(call: Call<List<Trip>>, response: Response<List<Trip>>) {
                    if (response.isSuccessful) {
                        _data.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<Trip>>, t: Throwable) {
                    Log.i(TAG, "onFailure: "+t.stackTraceToString())
                }

            })
        }
    }

    // Cancel any ongoing coroutines when the ViewModel is cleared
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }



}

