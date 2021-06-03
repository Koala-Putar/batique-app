package com.example.batiqueapp.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.batiqueapp.MainActivity
import com.example.batiqueapp.R
import com.example.batiqueapp.core.domain.model.Batik
import com.example.batiqueapp.core.ui.ViewModelFactory
import com.example.batiqueapp.databinding.ActivityDetailBinding
import java.util.*

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

        binding.backButton.setOnClickListener {
            val intent = Intent(this@DetailActivity, MainActivity::class.java)
            startActivity(intent)
        }

        val factory = ViewModelFactory.getInstance(this)
        detailViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val detailBatik = intent.getParcelableExtra<Batik>(EXTRA_DATA)
        val currentDate = Date()
        if(detailBatik != null) {
            showDetailBatik(detailBatik)
            detailViewModel.setLatestAccessDate(detailBatik, currentDate)
            binding.tvTitlebar.text = detailBatik.name

            binding.btnBuy.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tokopedia.com/search?st=product&q=Batik ${detailBatik.name}"));
                startActivity(browserIntent);
            }
        }
    }

    private fun showDetailBatik(detailBatik: Batik) {
        supportActionBar?.title = detailBatik.name
        binding.tvBatikName.text = detailBatik.name
        binding.tvBatikCategory.text = detailBatik.category
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

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_24))
        } else {
            binding.favoriteButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border_24))
        }
    }

}