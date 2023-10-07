package com.project.flight_booking.ui.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.project.flight_booking.R
import com.project.flight_booking.model.Trip
import com.project.flight_booking.viewmodels.MainViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.Duration
import java.time.format.DateTimeFormatter

class ListFlightsAdapter(private val items: List<Trip>,private val  viewModel: MainViewModel) : RecyclerView.Adapter<ListFlightsAdapter.ViewHolder>() {
    @RequiresApi(Build.VERSION_CODES.O)
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pattern: String?="yyyy-MM-dd'T'HH:mm:ss'+00:00'"
        var formatter = DateTimeFormatter.ofPattern(pattern)

        fun bind(item: Trip, viewModel: MainViewModel) {
            itemView.findViewById<TextView>(R.id.airlineName).text = item.airline.name
            itemView.findViewById<TextView>(R.id.price).text = item.price

            val departureDate: LocalDate = LocalDate.parse(item.departure.scheduled, formatter)
            val arriveDate: LocalDate = LocalDate.parse(item.arrival.scheduled, formatter)
            itemView.findViewById<TextView>(R.id.departureDate).text = departureDate.toString()
            itemView.findViewById<TextView>(R.id.arriveDate).text = arriveDate.toString()

            val departureTime: LocalTime = LocalTime.parse(item.departure.scheduled, formatter)
            val arriveTime: LocalTime = LocalTime.parse(item.arrival.scheduled, formatter)
            itemView.findViewById<TextView>(R.id.departureTime).text = departureTime.toString()
            itemView.findViewById<TextView>(R.id.arriveTime).text = arriveTime.toString()

            val duration = Duration.between(departureTime,arriveTime)

            itemView.findViewById<TextView>(R.id.duration).text =""+ duration.toHours()+"H,"+ duration.toMinutes()+"m"
            itemView.findViewById<TextView>(R.id.view_details).setOnClickListener {
                viewModel.setSlectedTrip(item)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_trip, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position],viewModel)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
