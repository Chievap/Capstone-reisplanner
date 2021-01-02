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
import com.example.capstonereisplanner.adapter.RouteAdapter
import com.example.capstonereisplanner.databinding.FragmentRouteBinding
import com.example.capstonereisplanner.entity.SavableStationRoute
import com.example.capstonereisplanner.entity.SavableTrip
import com.example.capstonereisplanner.viewmodel.RouteViewModel
import com.google.android.material.snackbar.Snackbar


const val FROM_STATION_ROUTE_NAME = "from_station_route_name"
const val FROM_STATION_ROUTE_TIME = "from_station_route_time"
const val TO_STATION_ROUTE_NAME = "to_station_route_name"
const val TO_STATION_ROUTE_TIME = "to_station_route_time"

class RouteFragment : Fragment() {

    private lateinit var binding: FragmentRouteBinding
    private lateinit var adapter: RouteAdapter
    private lateinit var mRecyclerView: RecyclerView
    private val viewModel: RouteViewModel by viewModels()

    private var stationRoutes = arrayListOf<SavableStationRoute>()

    var fromStationName = ""
    var fromStationTime = ""
    var toStationName = ""
    var toStationTime = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRouteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fromStationName = arguments?.getString(FROM_STATION_ROUTE_NAME).toString()
        fromStationTime= arguments?.getString(TO_STATION_ROUTE_TIME).toString()
        toStationName = arguments?.getString(TO_STATION_ROUTE_NAME).toString()
        toStationTime = arguments?.getString(FROM_STATION_ROUTE_TIME).toString()

        binding.ibStatistics.setOnClickListener{findNavController().navigate(R.id.action_routeFragment_to_statisticsFragment)}

        adapter = RouteAdapter(stationRoutes)

        mRecyclerView = binding.rcStations
        mRecyclerView.adapter = adapter

        mRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        binding.tvFrom.text = fromStationName
        binding.tvTo.text = toStationName
        binding.tvArrivalTime.text = toStationTime
        binding.tvDepartureTime.text = fromStationTime

        binding.bActivate.setOnClickListener { onActivateClick() }

        stationRoutes.add(SavableStationRoute(fromStationName.toString(), "4"))
        stationRoutes.add(SavableStationRoute(toStationName.toString(), "6"))
        adapter.notifyDataSetChanged()

    }

    private fun onActivateClick() {
        viewModel.saveTrip(SavableTrip(60,0,fromStationName,fromStationTime,toStationTime,5,toStationName,false))
        Snackbar.make(binding.view,"Trip now activated",Snackbar.LENGTH_SHORT).show()
    }
}