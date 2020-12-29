package com.example.capstonereisplanner.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonereisplanner.R
import com.example.capstonereisplanner.databinding.ItemStationBinding
import com.example.capstonereisplanner.entity.SavableStation
import kotlinx.android.synthetic.main.item_station.view.*

class SearchAdapter(
    private val stationList: List<SavableStation>,
    private val onClick: (SavableStation) -> Unit
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val stationItemBinding =
            ItemStationBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(
            stationItemBinding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stationList[position])
    }

    override fun getItemCount(): Int {
        return stationList.size
    }

    inner class ViewHolder(private val itemStation: ItemStationBinding) :
        RecyclerView.ViewHolder(itemStation.root) {

        init {
            itemStation.root.setOnClickListener{
                onClick(stationList[adapterPosition])
            }
        }
        fun bind(station: SavableStation) {
            itemStation.tvName.text = station?.name
        }
    }
}