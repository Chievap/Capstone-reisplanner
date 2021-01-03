package com.example.capstonereisplanner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonereisplanner.databinding.ItemTripHistoryBinding
import com.example.capstonereisplanner.entity.SavableTrip

class StatisticsAdapter(private val tripList: List<SavableTrip>) :
    RecyclerView.Adapter<StatisticsAdapter.ViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemHistoryBinding =
            ItemTripHistoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(
            itemHistoryBinding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tripList[position])
    }

    override fun getItemCount(): Int {
        return tripList.size
    }

    inner class ViewHolder(private val itemHistory: ItemTripHistoryBinding) :
        RecyclerView.ViewHolder(itemHistory.root) {

        fun bind(trip: SavableTrip) {
            itemHistory.tvFromStationName.text = trip.fromName
            itemHistory.tvToStationName.text = trip.destinationName
        }
    }
}