package com.example.capstonereisplanner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonereisplanner.R
import com.example.capstonereisplanner.adapter.TripAdapter
import com.example.capstonereisplanner.converter.TripConverter
import com.example.capstonereisplanner.databinding.FragmentSearchBinding
import com.example.capstonereisplanner.entity.SavableTrip
import com.example.capstonereisplanner.viewmodel.TripViewModel

const val FROM_STATION_NAME = "from_station_name"
const val FROM_STATION_CODE = "from_station_code"
const val TO_STATION_NAME = "to_station_name"
const val TO_STATION_CODE = "to_station_code"

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SearchFragment : Fragment() {

    private val viewModel: TripViewModel by viewModels()

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: TripAdapter
    private lateinit var mRecyclerView: RecyclerView

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
        val fromCode = arguments?.getString(FROM_STATION_CODE)
        val toCode = arguments?.getString(TO_STATION_CODE)

        mRecyclerView = binding.rc

        adapter = TripAdapter(tripList, this::onClick)
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        binding.ivStatistic.setOnClickListener { findNavController().navigate(R.id.action_SecondFragment_to_statisticsFragment) }

        if (fromCode != null && toCode != null) {
            viewModel.getTrip(fromCode, toCode)
        }

        binding.tvFrom.text = arguments?.getString(FROM_STATION_NAME)
        binding.tvTo.text = arguments?.getString(TO_STATION_NAME)

        observeTrips()
    }

    private fun observeTrips() {
        viewModel.trip.observe(viewLifecycleOwner, {
            this.tripList.clear()
            println(it.trips)
            this.tripList.addAll(tripConverter.convertTrips(it))
            this.adapter.notifyDataSetChanged()
        })
    }

    private fun onClick(savableTrip: SavableTrip) {
        val args = Bundle()
        args.putString(FROM_STATION_ROUTE_NAME, savableTrip.fromName)
        args.putString(FROM_STATION_ROUTE_TIME, savableTrip.departureTime)
        args.putString(TO_STATION_ROUTE_NAME, savableTrip.destinationName)
        args.putString(TO_STATION_ROUTE_TIME, savableTrip.arrivalTime)
        findNavController().navigate(R.id.action_SecondFragment_to_routeFragment, args)
    }
}