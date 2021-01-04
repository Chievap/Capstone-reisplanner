package com.example.capstonereisplanner.ui

import android.content.Intent
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
import com.example.capstonereisplanner.model.FavoriteTrip
import com.example.capstonereisplanner.viewmodel.ActiveTripViewModel
import com.example.capstonereisplanner.viewmodel.FavoriteTripViewModel
import com.example.capstonereisplanner.viewmodel.RouteViewModel
import com.google.android.material.snackbar.Snackbar


const val FROM_STATION_ROUTE_NAME = "from_station_route_name"
const val FROM_STATION_ROUTE_TIME = "from_station_route_time"
const val FROM_STATION_ROUTE_TRACK = "from_station_route_track"
const val FROM_STATION_ROUTE_CODE = "from_station_route_code"
const val TO_STATION_ROUTE_NAME = "to_station_route_name"
const val TO_STATION_ROUTE_TIME = "to_station_route_time"
const val TO_STATION_ROUTE_TRACK = "to_station_route_track"
const val TO_STATION_ROUTE_CODE = "to_station_route_code"
const val TRAVEL_TIME = "travel_time"

class RouteFragment : Fragment() {

    private lateinit var binding: FragmentRouteBinding
    private lateinit var adapter: RouteAdapter
    private lateinit var mRecyclerView: RecyclerView
    private val viewModel: RouteViewModel by viewModels()
    private val activeTripViewModel: ActiveTripViewModel by viewModels()
    private val favoriteTripViewModel: FavoriteTripViewModel by viewModels()

    private var stationRoutes = arrayListOf<SavableStationRoute>()

    private var fromStationName = ""
    private var fromStationTime = ""
    private var fromStationTrack = 0
    private var fromStationCode = ""
    private var toStationName = ""
    private var toStationTime = ""
    private var toStationTrack = 0
    private var toStationCode = ""
    private var travelTime = 0

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
        fromStationTime = arguments?.getString(FROM_STATION_ROUTE_TIME).toString()
        fromStationTrack = requireArguments().getInt(FROM_STATION_ROUTE_TRACK)
        fromStationCode = arguments?.getString(FROM_STATION_ROUTE_CODE).toString()
        toStationName = arguments?.getString(TO_STATION_ROUTE_NAME).toString()
        toStationTime = arguments?.getString(TO_STATION_ROUTE_TIME).toString()
        toStationTrack = requireArguments().getInt(TO_STATION_ROUTE_TRACK)
        toStationCode = arguments?.getString(TO_STATION_ROUTE_CODE).toString()
        travelTime = requireArguments().getInt(TRAVEL_TIME)

        setBindings()

        // Add both stations to the list
        stationRoutes.add(SavableStationRoute(fromStationName, fromStationTrack.toString()))
        stationRoutes.add(SavableStationRoute(toStationName, toStationTrack.toString()))
        adapter.notifyDataSetChanged()

        observeActiveTrip()
    }

    private fun setBindings() {
        binding.ibStatistics.setOnClickListener { findNavController().navigate(R.id.action_routeFragment_to_statisticsFragment) }
        binding.bViewOnMap.setOnClickListener {
            val intent = Intent(context, MapsActivity::class.java)
            startActivity(intent)
        }
        setMapButton(false)

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
        binding.bFavoriteTrip.setOnClickListener { saveFavoriteTrip() }
    }

    private fun observeActiveTrip() {
        activeTripViewModel.trips.observe(viewLifecycleOwner, {
            if(!it.isNullOrEmpty()){
                if(it[0].fromName == fromStationName && it[0].destinationName == toStationName){
                    setMapButton(true)
                }
            }
        })
    }

    private fun saveFavoriteTrip() {
        favoriteTripViewModel.createFavoriteTrip(
            FavoriteTrip(
                fromStationName,
                toStationName,
                fromStationCode,
                toStationCode
            )
        )
        observeCreateSuccess()
    }

    private fun observeCreateSuccess() {
        favoriteTripViewModel.createSuccess.observe(viewLifecycleOwner, {
            Snackbar.make(
                binding.view,
                "Successfully added trip to favorites",
                Snackbar.LENGTH_SHORT
            ).show()
        })
    }

    private fun onActivateClick() {
        val savableTrip = SavableTrip(
            travelTime,
            0,
            fromStationName,
            fromStationTime,
            toStationTime,
            5,
            toStationName,
            false,
            fromStationTrack,
            toStationTrack
        )
        // Delete the current active trip and save the new one
        activeTripViewModel.deleteTrips()
        viewModel.saveTrip(
            savableTrip
        )
        // Enable the map button
        setMapButton(true)
        Snackbar.make(binding.view, "Trip now activated", Snackbar.LENGTH_SHORT).show()
        activeTripViewModel.saveTrip(savableTrip)
    }

    private fun setMapButton(value:Boolean){
        // Enable the map button
        binding.bViewOnMap.isEnabled = value
        binding.bViewOnMap.isClickable = value
    }
}