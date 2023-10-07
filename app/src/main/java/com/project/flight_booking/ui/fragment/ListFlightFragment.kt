package com.project.flight_booking.ui.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.flight_booking.R
import com.project.flight_booking.apis.RetrofitClient
import com.project.flight_booking.repositry.MainRepositry
import com.project.flight_booking.ui.adapters.ListFlightsAdapter
import com.project.flight_booking.viewmodels.MainViewModel
import com.project.flight_booking.viewmodels.MainViewModelFactory

class ListFlightFragment : Fragment() {
    private lateinit var listFlightsAdapter: ListFlightsAdapter
    private var recyclerView: RecyclerView? = null
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val repository = MainRepositry(RetrofitClient.apiService)
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        setObservers()
    }

    private fun setObservers() {
        viewModel.data.observe(this) { trips ->
            trips?.let { it ->
                it.asSequence().iterator().forEach {trip->
                    Log.i(ContentValues.TAG, " onCreate: $trip")
                }
                recyclerView?.layoutManager = LinearLayoutManager(context)


                listFlightsAdapter = ListFlightsAdapter(it)
                recyclerView?.adapter = listFlightsAdapter
            }
            // Update UI with the data

        }
        viewModel.fetchData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View= inflater.inflate(R.layout.fragment_list_flights, container, false);

        recyclerView= view.findViewById<RecyclerView>(R.id.recyclerView)
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ListFlightFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}