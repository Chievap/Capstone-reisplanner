package com.example.capstonereisplanner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonereisplanner.adapter.StatisticsAdapter
import com.example.capstonereisplanner.databinding.FragmentStatisticsBinding
import com.example.capstonereisplanner.entity.SavableTrip
import com.example.capstonereisplanner.viewmodel.RouteViewModel

class StatisticsFragment : Fragment() {

    private lateinit var binding: FragmentStatisticsBinding
    private lateinit var  adapter: StatisticsAdapter
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
        })
    }

}