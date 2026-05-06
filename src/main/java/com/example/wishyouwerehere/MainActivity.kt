package com.example.wishyouwerehere

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.wishyouwerehere.databinding.ActivityMainBinding
import com.example.wishyouwerehere.model.Location

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LocationViewModel
    private lateinit var adapter: LocationAdapter


    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val updated = result.data?.getParcelableExtra<Location>("location")
            updated?.let { viewModel.updateRating(it) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[LocationViewModel::class.java]

        adapter = LocationAdapter { location ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("location", location)
            launcher.launch(intent)
        }

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = adapter

        viewModel.locations.observe(this) {
            adapter.updateList(it)
        }
    }
}