package com.example.wishyouwerehere

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wishyouwerehere.model.Location

class LocationViewModel : ViewModel() {

    private val _locations = MutableLiveData(
        mutableListOf(
            Location("Gold Coast", "QLD, Australia", "Jan 2026", 4.5f, R.drawable.goldcoast),
            Location("Sydney Opera House", "NSW, Australia", "Dec 2025", 5f, R.drawable.sydney),
            Location("Great Barrier Reef", "QLD, Australia", "Nov 2025", 4f, R.drawable.reef),
            Location("Melbourne CBD", "VIC, Australia", "Oct 2025", 3.5f, R.drawable.melbourne)
        )
    )

    val locations: LiveData<MutableList<Location>> = _locations

    fun updateRating(updated: Location) {
        val list = _locations.value
        val index = list?.indexOfFirst { it.name == updated.name }
        if (index != null && index != -1) {
            list[index] = updated
            _locations.value = list
        }
    }
}