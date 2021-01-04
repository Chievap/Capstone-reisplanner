package com.example.capstonereisplanner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonereisplanner.databinding.ItemFavoriteRouteBinding
import com.example.capstonereisplanner.entity.SavableTrip

class FavoriteTripAdapter(private val trips: List<SavableTrip>):
    RecyclerView.Adapter<FavoriteTripAdapter.ViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val itemFavoriteRouteBinding =
            ItemFavoriteRouteBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(
            itemFavoriteRouteBinding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trips[position])
    }

    override fun getItemCount(): Int {
        return trips.size
    }

    inner class ViewHolder(private val itemRoute: ItemFavoriteRouteBinding) :
        RecyclerView.ViewHolder(itemRoute.root) {

        fun bind(trip: SavableTrip) {
            itemRoute.tvArrivaltime.text = trip.arrivalTime
            itemRoute.tvDeparturetime.text = trip.departureTime
        }
    }
}