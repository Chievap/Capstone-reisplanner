package com.example.capstonereisplanner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonereisplanner.databinding.ItemRouteBinding
import com.example.capstonereisplanner.entity.SavableTrip

class TripAdapter(
    private val trips: List<SavableTrip>
// private val onClick: (SavableStation) -> Unit
):
RecyclerView.Adapter<TripAdapter.ViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val routeItemBinding =
            ItemRouteBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(
            routeItemBinding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(trips[position])
    }

    override fun getItemCount(): Int {
        return trips.size
    }

    inner class ViewHolder(private val itemRoute: ItemRouteBinding) :
        RecyclerView.ViewHolder(itemRoute.root) {

        init {
            itemRoute.root.setOnClickListener {
                // onClick(stationList[adapterPosition])
                println("Clicken on trip")
            }
        }

        fun bind(trip: SavableTrip) {
            itemRoute.tvDeparture.text = trip.departureTime
            itemRoute.tvArrival.text = trip.arrivalTime
            itemRoute.tvTransfers.text = trip.transfers.toString()
            itemRoute.tvTime.text = trip.plannedDurationInMinutes.toString()
        }
    }
}