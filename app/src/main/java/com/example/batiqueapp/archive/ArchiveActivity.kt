package com.example.batiqueapp.archive

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batiqueapp.core.domain.model.Category
import com.example.batiqueapp.core.ui.ArchiveAdapter
import com.example.batiqueapp.core.ui.ViewModelFactory
import com.example.batiqueapp.databinding.ActivityArchiveBinding
import com.example.batiqueapp.detail.DetailActivity

class ArchiveActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var archiveViewModel: ArchiveViewModel
    private lateinit var binding: ActivityArchiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArchiveBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val detailCategory = intent.getParcelableExtra<Category>(EXTRA_DATA)
        showDetailCategory(detailCategory)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val archiveAdapter = ArchiveAdapter()

        val factory = ViewModelFactory.getInstance(this)
        archiveViewModel = ViewModelProvider(this, factory)[ArchiveViewModel::class.java]

        if (detailCategory != null) {
            archiveViewModel.setSelectedCategory(detailCategory.name)
        }

        archiveViewModel.categoryBy.observe(this, { dataBatik ->
            archiveAdapter.setData(dataBatik)
        })

        with(binding.rvBatik) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = archiveAdapter
        }

        archiveAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

    }

    private fun showDetailCategory(detailCategory: Category?) {
        detailCategory?.let {
            supportActionBar?.title = detailCategory.name
            binding.tvTitlebar.text = detailCategory.name
            binding.tvDescriptionBar.text = detailCategory.description
        }
    }
}