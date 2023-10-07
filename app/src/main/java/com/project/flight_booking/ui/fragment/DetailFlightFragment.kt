package com.project.flight_booking.ui.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.project.flight_booking.R
import com.project.flight_booking.apis.RetrofitClient
import com.project.flight_booking.model.Trip
import com.project.flight_booking.repositry.MainRepositry
import com.project.flight_booking.viewmodels.MainViewModel
import com.project.flight_booking.viewmodels.MainViewModelFactory
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DetailFlightFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val repository = MainRepositry(RetrofitClient.apiService)
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java)

        setObservers()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setObservers() {
        viewModel.selectedtrip.value?.let {item->
           refreshView(item)

        }
        viewModel.selectedtrip.observe(this){
            refreshView(it)
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun refreshView(item: Trip) {
        var pattern: String?="yyyy-MM-dd'T'HH:mm:ss'+00:00'"
        var formatter = DateTimeFormatter.ofPattern(pattern)
        view?.let {
            it.findViewById<TextView>(R.id.airlineName)?.text = item.airline.name
            it.findViewById<TextView>(R.id.price).text = item.price

            val departureDate: LocalDate = LocalDate.parse(item.departure.scheduled, formatter)
            val arriveDate: LocalDate = LocalDate.parse(item.arrival.scheduled, formatter)
            it.findViewById<TextView>(R.id.departureDate).text = departureDate.toString()
            it.findViewById<TextView>(R.id.arriveDate).text = arriveDate.toString()

            val departureTime: LocalTime = LocalTime.parse(item.departure.scheduled, formatter)
            val arriveTime: LocalTime = LocalTime.parse(item.arrival.scheduled, formatter)
            it.findViewById<TextView>(R.id.departureTime).text = departureTime.toString()
            it.findViewById<TextView>(R.id.arriveTime).text = arriveTime.toString()

            val duration = Duration.between(departureTime,arriveTime)

            it.findViewById<TextView>(R.id.duration).text =""+ duration.toHours()+"H,"+ duration.toMinutes()+"m"

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view:View = inflater.inflate(R.layout.fragment_detail_flight, container, false);

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            DetailFlightFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}