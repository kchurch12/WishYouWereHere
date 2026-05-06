package com.example.wishyouwerehere

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wishyouwerehere.databinding.ItemLocationBinding
import com.example.wishyouwerehere.model.Location

class LocationAdapter(
    private val onClick: (Location) -> Unit
) : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {

    private var locations = listOf<Location>()

    fun updateList(newList: List<Location>) {
        locations = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemLocationBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLocationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = locations[position]

        holder.binding.locationImage.setImageResource(location.imageResId)
        holder.binding.locationTitle.text = location.name
        holder.binding.locationRating.rating = location.rating

        holder.binding.root.setOnClickListener {
            onClick(location)
        }
    }

    override fun getItemCount() = locations.size
}