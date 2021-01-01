package com.example.capstonereisplanner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonereisplanner.databinding.ItemStationRouteBinding
import com.example.capstonereisplanner.entity.SavableStationRoute

class RouteAdapter(private val stationRoutes: List<SavableStationRoute>) :
    RecyclerView.Adapter<RouteAdapter.ViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RouteAdapter.ViewHolder {
        context = parent.context
        val routeItemBinding =
            ItemStationRouteBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(
            routeItemBinding
        )
    }

    override fun onBindViewHolder(holder: RouteAdapter.ViewHolder, position: Int) {
        holder.bind(stationRoutes[position])
    }

    override fun getItemCount(): Int {
        return stationRoutes.size
    }

    inner class ViewHolder(private val itemRoute: ItemStationRouteBinding) :
        RecyclerView.ViewHolder(itemRoute.root) {

        fun bind(trip: SavableStationRoute) {
            itemRoute.tvStationName.text = trip.name
            itemRoute.tvTrackNumber.text = trip.track
        }
    }

}