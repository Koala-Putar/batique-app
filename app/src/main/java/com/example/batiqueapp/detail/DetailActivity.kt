package com.example.batiqueapp.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.batiqueapp.R
import com.example.batiqueapp.core.domain.model.Batik
import com.example.batiqueapp.core.ui.ViewModelFactory
import com.example.batiqueapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var detailViewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val detailBatik = intent.getParcelableExtra<Batik>(EXTRA_DATA)
        showDetailBatik(detailBatik)
    }

    private fun showDetailBatik(detailBatik: Batik?) {
        detailBatik?.let {
            supportActionBar?.title = detailBatik.name
            binding.tvBatikDescription.text = detailBatik.description
            Glide.with(this@DetailActivity)
                    .load(detailBatik.image)
                    .into(binding.photo)

            var statusFavorite = detailBatik.isFavorite
            setStatusFavorite(statusFavorite)
            binding.favoriteButton.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteBatik(detailBatik, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            binding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }

}