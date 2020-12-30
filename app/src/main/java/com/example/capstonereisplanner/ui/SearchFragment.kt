package com.example.capstonereisplanner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.capstonereisplanner.R
import com.example.capstonereisplanner.adapter.TripAdapter
import com.example.capstonereisplanner.converter.TripConverter
import com.example.capstonereisplanner.databinding.FragmentHomeBinding
import com.example.capstonereisplanner.databinding.FragmentSearchBinding
import com.example.capstonereisplanner.entity.SavableTrip
import com.example.capstonereisplanner.viewmodel.StationViewModel
import com.example.capstonereisplanner.viewmodel.TripViewModel

const val FROM_STATION_NAME = "from_station_name"
const val FROM_STATION_CODE = "from_station_code"
const val TO_STATION_NAME = "to_station_name"
const val TO_STATION_CODE = "from_station_code"

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SearchFragment : Fragment() {

    private val viewModel: TripViewModel by viewModels()

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: TripAdapter

    private var tripList = arrayListOf<SavableTrip>()
    private val tripConverter: TripConverter = TripConverter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = TripAdapter(tripList)


        println(arguments?.getString(FROM_STATION_NAME))
        println(arguments?.getString(TO_STATION_NAME))

        binding.tvFrom.text = arguments?.getString(FROM_STATION_NAME)
        binding.tvTo.text = arguments?.getString(TO_STATION_NAME)
    }
}