package com.example.capstonereisplanner.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonereisplanner.R
import com.example.capstonereisplanner.adapter.FavoriteTripAdapter
import com.example.capstonereisplanner.adapter.SearchAdapter
import com.example.capstonereisplanner.adapter.TripAdapter
import com.example.capstonereisplanner.converter.StationConverter
import com.example.capstonereisplanner.converter.TripConverter
import com.example.capstonereisplanner.databinding.FragmentHomeBinding
import com.example.capstonereisplanner.entity.SavableStation
import com.example.capstonereisplanner.entity.SavableTrip
import com.example.capstonereisplanner.model.FavoriteTrip
import com.example.capstonereisplanner.viewmodel.ActiveTripViewModel
import com.example.capstonereisplanner.viewmodel.FavoriteTripViewModel
import com.example.capstonereisplanner.viewmodel.StationViewModel
import com.example.capstonereisplanner.viewmodel.TripViewModel
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {
    private val viewModel: StationViewModel by viewModels()
    private val activeTripViewModel: ActiveTripViewModel by viewModels()
    private val favoriteTripViewModel: FavoriteTripViewModel by viewModels()
    private val tripViewModel: TripViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mSearchTextFrom: EditText
    private lateinit var mSearchTextTo: EditText
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var changeStationSearch: EditText
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var favoriteTripAdapter: FavoriteTripAdapter

    private var fromStation: String = ""
    private var toStation: String = ""
    private val tripConverter: TripConverter = TripConverter()

    private val stationList = arrayListOf<SavableStation>()
    private val stationSuggestions = arrayListOf<SavableStation>()
    private val stationConverter = StationConverter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel.getStations()
        favoriteTripViewModel.getFavoriteTrip()
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchAdapter = SearchAdapter(stationSuggestions, this::onStationClick)
        stationSuggestions.clear()
        searchAdapter.notifyDataSetChanged()

        setBindings()

        setTextChangeListeners()

        observeStations()
        observeActiveTrip()
        observeFavoriteTrip()
    }

    private fun setTextChangeListeners() {
        mSearchTextFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterSuggestions(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                changeStationSearch = mSearchTextFrom
            }

        })

        mSearchTextTo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterSuggestions(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                changeStationSearch = mSearchTextTo
            }

        })
    }

    private fun setBindings() {
        mSearchTextFrom = binding.searchTextFrom
        changeStationSearch = mSearchTextFrom
        mSearchTextTo = binding.searchTextTo
        mRecyclerView = binding.listView

        binding.bSearch.setOnClickListener { navigateToSearch() }
        binding.ivStatistics.setOnClickListener { findNavController().navigate(R.id.action_FirstFragment_to_statisticsFragment) }
        binding.activeRoute.activeGroup.visibility = View.GONE
        binding.favoriteTripGroup.visibility = View.GONE

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.adapter = searchAdapter

        mRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    private fun observeFavoriteTrip() {
        var favoriteTrip: FavoriteTrip
        favoriteTripViewModel.favoriteTrip.observe(viewLifecycleOwner, {
            if (it != null && it.fromCode != "null" && it.toCode != "null") {
                favoriteTrip = it
                binding.favoriteTripGroup.visibility = View.VISIBLE
                binding.favoriteTrip.tvFavoriteFrom.text = favoriteTrip.fromName
                binding.favoriteTrip.tvFavoriteto.text = favoriteTrip.toName

                tripViewModel.getTrip(favoriteTrip.fromCode, favoriteTrip.toCode)
                observeTripResult()
            }
        })
    }

    private fun observeTripResult() {
        tripViewModel.trip.observe(viewLifecycleOwner, {
            favoriteTripAdapter = FavoriteTripAdapter(tripConverter.convertTrips(it))

            binding.favoriteTrip.rcFavoriteTrips.adapter = favoriteTripAdapter

            binding.favoriteTrip.rcFavoriteTrips.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

            favoriteTripAdapter.notifyDataSetChanged()
        })
    }

    private fun filterSuggestions(searchTerm: String) {
        stationSuggestions.clear()
        stationSuggestions.addAll(stationList.filter { station: SavableStation ->
            station.name.toLowerCase(Locale.ROOT).contains(
                searchTerm.toLowerCase(Locale.ROOT)
            )
        }.toList())
        searchAdapter.notifyDataSetChanged()
    }

    private fun observeStations() {
        viewModel.stations.observe(viewLifecycleOwner, {
            this.stationList.clear()
            this.stationList.addAll(stationConverter.convertStations(it.payload))
            this.searchAdapter.notifyDataSetChanged()
        })
    }

    private fun observeActiveTrip() {
        activeTripViewModel.trips.observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                val savableTrip = it[0]
                binding.activeRoute.tvFromActive.text = savableTrip.fromName
                binding.activeRoute.tvToActive.text = savableTrip.destinationName
                binding.activeRoute.activeGroup.visibility = View.VISIBLE
                binding.activeRoute.cl.setOnClickListener {
                    val args = Bundle()
                    args.putString(FROM_STATION_ROUTE_NAME, savableTrip.fromName)
                    args.putString(FROM_STATION_ROUTE_TIME, savableTrip.departureTime)
                    args.putInt(FROM_STATION_ROUTE_TRACK, savableTrip.fromTrack)
                    args.putString(TO_STATION_ROUTE_NAME, savableTrip.destinationName)
                    args.putString(TO_STATION_ROUTE_TIME, savableTrip.arrivalTime)
                    args.putInt(TO_STATION_ROUTE_TRACK, savableTrip.toTrack)
                    args.putInt(TRAVEL_TIME, savableTrip.plannedDurationInMinutes)
                    findNavController().navigate(R.id.action_FirstFragment_to_routeFragment, args)
                }
            }
        })
    }

    private fun navigateToSearch() {
        val fromStation: SavableStation =
            stationList.first { station: SavableStation -> station.name == this.fromStation }
        val toStation =
            stationList.first { station: SavableStation -> station.name == this.toStation }
        val args = Bundle()
        args.putString(FROM_STATION_NAME, fromStation.name)
        args.putString(FROM_STATION_CODE, fromStation.code)
        args.putString(TO_STATION_NAME, toStation.name)
        args.putString(TO_STATION_CODE, toStation.code)

        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, args)
    }

    private fun onStationClick(station: SavableStation) {
        this.changeStationSearch.text.clear()
        this.changeStationSearch.text.append(station.name)

        if (this.fromStation.isEmpty()) {
            this.fromStation = station.name
        } else {
            this.toStation = station.name
        }
        this.stationSuggestions.clear()
        this.searchAdapter.notifyDataSetChanged()
    }

}


