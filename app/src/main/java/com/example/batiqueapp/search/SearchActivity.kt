package com.example.batiqueapp.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batiqueapp.MainActivity
import com.example.batiqueapp.core.ui.ArchiveAdapter
import com.example.batiqueapp.core.ui.ViewModelFactory
import com.example.batiqueapp.databinding.ActivitySearchBinding
import com.example.batiqueapp.detail.DetailActivity

class SearchActivity : AppCompatActivity() {
    companion object {
        const val SEARCH_QUERY = "search_query"
    }

    private lateinit var searchViewModel: SearchViewModel
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchQuery = intent.getStringExtra(SEARCH_QUERY)
        binding.backButton.setOnClickListener {
            val intent = Intent(this@SearchActivity, MainActivity::class.java)
            startActivity(intent)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val archiveAdapter = ArchiveAdapter()

        val factory = ViewModelFactory.getInstance(this@SearchActivity)
        searchViewModel = ViewModelProvider(this@SearchActivity, factory)[SearchViewModel::class.java]

        if (searchQuery.toString() != null) {
            searchViewModel.showBySearch(searchQuery.toString()).observe(this@SearchActivity, { dataBatik ->
                if(dataBatik != null) {
                    archiveAdapter.setData(dataBatik)
                }
            })
        }

        with(binding.rvBatik) {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            setHasFixedSize(true)
            this.adapter = archiveAdapter
        }

        archiveAdapter.onItemClick = { selectedData ->
            val intent = Intent(this@SearchActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

    }

}