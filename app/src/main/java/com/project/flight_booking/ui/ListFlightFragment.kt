package com.project.flight_booking.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.flight_booking.R

class ListFlightFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_flights, container, false);
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