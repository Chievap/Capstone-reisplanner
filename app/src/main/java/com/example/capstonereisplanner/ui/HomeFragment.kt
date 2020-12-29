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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonereisplanner.adapter.SearchAdapter
import com.example.capstonereisplanner.converter.StationConverter
import com.example.capstonereisplanner.databinding.FragmentHomeBinding
import com.example.capstonereisplanner.entity.SavableStation
import com.example.capstonereisplanner.viewmodel.StationViewModel
import kotlinx.coroutines.withTimeout

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class HomeFragment : Fragment() {
    private val viewModel: StationViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mSearchTextFrom: EditText
    private lateinit var mSearchTextTo: EditText
    private lateinit var mRecyclerView: RecyclerView
    private val stationList = arrayListOf<SavableStation>()
    private lateinit var searchAdapter: SearchAdapter

    private val stationConverter = StationConverter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        viewModel.getStations()
        binding = FragmentHomeBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchAdapter = SearchAdapter(stationList, this::onStationClick)
        mSearchTextFrom = binding.searchTextFrom
        mSearchTextTo = binding.searchTextTo
        mRecyclerView = binding.listView

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.adapter = searchAdapter
        mRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        mSearchTextFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                println(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        mSearchTextTo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                println(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        observeStations()

        viewModel.stations.observe(viewLifecycleOwner,{
            println("FUCK MY LIFE")
        })
    }

    private fun observeStations() {
        viewModel.stations.observe(viewLifecycleOwner, {
            this.stationList.clear()
            this.stationList.addAll(stationConverter.convertStations(it.payload))
            this.stationList.add(SavableStation("test", "test", "test"))
            this.searchAdapter.notifyDataSetChanged()
        })
    }

    private fun onStationClick(station: SavableStation) {
        println(station.name)
    }

}


