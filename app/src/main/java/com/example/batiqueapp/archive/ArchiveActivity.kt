package com.example.batiqueapp.archive

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batiqueapp.MainActivity
import com.example.batiqueapp.core.domain.model.Category
import com.example.batiqueapp.core.ui.ArchiveAdapter
import com.example.batiqueapp.core.ui.ViewModelFactory
import com.example.batiqueapp.databinding.ActivityArchiveBinding
import com.example.batiqueapp.detail.DetailActivity

class ArchiveActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_NAME = "extra_name"
    }

    private lateinit var archiveViewModel: ArchiveViewModel
    private lateinit var binding: ActivityArchiveBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArchiveBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            val intent = Intent(this@ArchiveActivity, MainActivity::class.java)
            startActivity(intent)
        }

        var categoryName: String? = null
        var categoryDescription: String? = null

        var detailCategory: Category? = intent.getParcelableExtra(EXTRA_DATA)
        if(detailCategory != null) {
            categoryName = detailCategory.name
            categoryDescription = detailCategory.description
        } else {
            categoryName = intent.getStringExtra(EXTRA_NAME)
        }

        if(categoryDescription == null) {
            binding.tvDescriptionBar.visibility = View.GONE
        } else {
            binding.tvDescriptionBar.text = categoryDescription
            binding.tvDescriptionBar.visibility = View.VISIBLE
        }

        binding.tvTitlebar.text = categoryName

        val archiveAdapter = ArchiveAdapter()

        val factory = ViewModelFactory.getInstance(this@ArchiveActivity)
        archiveViewModel = ViewModelProvider(this@ArchiveActivity, factory)[ArchiveViewModel::class.java]

        if (categoryName != null) {
            archiveViewModel.showCategoryBy(categoryName.toString().trimIndent()).observe(this@ArchiveActivity, { dataBatik ->
                if(dataBatik != null) {
                    archiveAdapter.setData(dataBatik)
                }
            })
        }

        with(binding.rvBatik) {
            layoutManager = LinearLayoutManager(this@ArchiveActivity)
            setHasFixedSize(true)
            this.adapter = archiveAdapter
        }

        archiveAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@ArchiveActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

    }
}