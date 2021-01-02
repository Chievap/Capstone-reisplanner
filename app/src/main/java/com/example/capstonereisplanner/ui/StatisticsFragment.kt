package com.example.capstonereisplanner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonereisplanner.adapter.StatisticsAdapter
import com.example.capstonereisplanner.databinding.FragmentStatisticsBinding
import com.example.capstonereisplanner.entity.SavableTrip
import com.example.capstonereisplanner.viewmodel.RouteViewModel

class StatisticsFragment : Fragment() {

    private lateinit var binding: FragmentStatisticsBinding
    private lateinit var adapter: StatisticsAdapter
    private lateinit var mRecyclerView: RecyclerView
    private val viewModel: RouteViewModel by viewModels()

    private val tripList = arrayListOf<SavableTrip>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentStatisticsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = StatisticsAdapter(tripList)
        mRecyclerView = binding.rcHistory
        mRecyclerView.adapter = adapter
        mRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.setHasFixedSize(true)

        observeTripList()
    }

    private fun observeTripList() {
        viewModel.trips.observe(viewLifecycleOwner, {
            tripList.clear()
            tripList.addAll(it)
            adapter.notifyDataSetChanged()

            binding.tvMostVisitedStation.text = getMostVisitedStation(tripList)
            binding.tvMostTraveled.text = getMostTraveledTrip(tripList)
            binding.tvHoursTraveled.text = getHoursTraveled(tripList)
        })
    }

    private fun getMostVisitedStation(trips: List<SavableTrip>): String {
        val mapFrom: HashMap<String, Int> = HashMap()
        val mapTo: HashMap<String, Int> = HashMap()

        for (name in trips) {
            val fromValue = mapFrom[name.fromName]
            val toValue = mapTo[name.destinationName]
            mapFrom[name.fromName] = if (fromValue == null) 1 else fromValue + 1
            mapTo[name.destinationName] = if (toValue == null) 1 else toValue + 1
        }

        var fromMax: MutableMap.MutableEntry<String, Int>? = null
        var toMax: MutableMap.MutableEntry<String, Int>? = null

        for (entry: MutableMap.MutableEntry<String, Int> in mapFrom.entries) {
            if (fromMax == null || entry.value > fromMax.value) {
                fromMax = entry
            }
        }

        for (entry: MutableMap.MutableEntry<String, Int> in mapTo.entries) {
            if (toMax == null || entry.value > toMax.value) {
                toMax = entry
            }
        }
        var mostCommon = ""

        if (fromMax != null && toMax != null) {
            mostCommon = if (fromMax.value > toMax.value) fromMax.key else toMax.key
        }
        return mostCommon
    }

    private fun getMostTraveledTrip(trips: List<SavableTrip>): String {
        val traveledTrip: HashMap<String, Int> = HashMap()

        for (trip in trips) {
            val tripStationNames = trip.fromName.plus(" -> ".plus(trip.destinationName))
            val counter = traveledTrip[tripStationNames]
            traveledTrip[tripStationNames] = if (counter == null) 1 else counter + 1
        }

        var max: MutableMap.MutableEntry<String, Int>? = null

        for (entry: MutableMap.MutableEntry<String, Int> in traveledTrip) {
            if (max == null || entry.value > max.value) {
                max = entry
            }
        }
        var mostCommon = ""

        if (max != null) {
            mostCommon = max.key
        }
        return mostCommon
    }

    private fun getHoursTraveled(trips: List<SavableTrip>): String {
        var totalTime = 0

        for(trip in trips){
            totalTime += trip.plannedDurationInMinutes
        }

        val hours = totalTime / 60
        val minutes = totalTime % 60

        return makeTwoDigits(hours).plus(":").plus(makeTwoDigits(minutes))
    }

    private fun makeTwoDigits(number: Int):String{
        if(number.toString().trim().length == 1){
            return "0".plus(number.toString())
        }
        return number.toString()
    }

}