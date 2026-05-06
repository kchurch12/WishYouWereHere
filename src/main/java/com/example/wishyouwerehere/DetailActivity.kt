package com.example.wishyouwerehere

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wishyouwerehere.databinding.ActivityDetailBinding
import com.example.wishyouwerehere.model.Location

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var location: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        location = intent.getParcelableExtra("location")!!

        binding.detailName.text = location.name
        binding.detailPlace.text = location.country
        binding.detailDate.text = location.date
        binding.detailImage.setImageResource(location.imageResId)
        binding.detailRating.rating = location.rating

        binding.detailRating.setOnRatingBarChangeListener { _, rating, _ ->
            location.rating = rating
        }
    }


    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            sendResult()
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        sendResult()
        super.onBackPressed()
    }

    private fun sendResult() {
        val intent = Intent()
        intent.putExtra("location", location)
        setResult(RESULT_OK, intent)
    }
}