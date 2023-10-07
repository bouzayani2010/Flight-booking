package com.project.flight_booking.ui.activity

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.project.flight_booking.R
import com.project.flight_booking.apis.RetrofitClient
import com.project.flight_booking.repositry.MainRepositry
import com.project.flight_booking.viewmodels.MainViewModel
import com.project.flight_booking.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


    //    navController.navigate(R.id.action_list_to_details)




        val repository = MainRepositry(RetrofitClient.apiService)
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.selectedtrip.observe(this){trip->
            trip?.let {
                navController.navigate(R.id.action_list_to_details)
            }
        }

       // viewModel.fetchData()
    }
}