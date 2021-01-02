package com.example.capstonereisplanner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonereisplanner.adapter.RouteAdapter
import com.example.capstonereisplanner.databinding.FragmentRouteBinding
import com.example.capstonereisplanner.entity.SavableStationRoute


const val FROM_STATION_ROUTE_NAME = "from_station_route_name"
const val FROM_STATION_ROUTE_TIME = "from_station_route_time"
const val TO_STATION_ROUTE_NAME = "to_station_route_name"
const val TO_STATION_ROUTE_TIME = "to_station_route_time"

class RouteFragment : Fragment() {

    private lateinit var binding: FragmentRouteBinding
    private lateinit var adapter: RouteAdapter
    private lateinit var mRecyclerView: RecyclerView

    private var stationRoutes = arrayListOf<SavableStationRoute>()

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
        val fromStationName = arguments?.getString(FROM_STATION_ROUTE_NAME)
        val fromStationTime = arguments?.getString(TO_STATION_ROUTE_TIME)
        val toStationName =  arguments?.getString(TO_STATION_ROUTE_NAME)
        val toStationTime = arguments?.getString(FROM_STATION_ROUTE_TIME)
        adapter = RouteAdapter(stationRoutes)

        mRecyclerView = binding.rcStations
        mRecyclerView.adapter = adapter

        mRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        binding.tvFrom.text = fromStationName
        binding.tvTo.text = toStationName
        binding.tvArrivalTime.text = toStationTime
        binding.tvDepartureTime.text = fromStationTime

        stationRoutes.add(SavableStationRoute(fromStationName.toString(),"4"))
        stationRoutes.add(SavableStationRoute(toStationName.toString(),"6"))
        adapter.notifyDataSetChanged()

    }
}