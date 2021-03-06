package com.example.batiqueapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.batiqueapp.R
import com.example.batiqueapp.core.data.Resource
import com.example.batiqueapp.core.ui.BatikAdapter
import com.example.batiqueapp.core.ui.ViewModelFactory
import com.example.batiqueapp.databinding.FragmentHomeBinding
import com.example.batiqueapp.detail.DetailActivity
import com.example.batiqueapp.search.SearchActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            Glide.with(this)
                .load(R.drawable.batique)
                .into(binding.ivLogo)

            val batikAdapter = BatikAdapter()
            batikAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

            homeViewModel.batik.observe(viewLifecycleOwner, { batik ->
                if (batik != null) {
                    when (batik) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            batikAdapter.setData(batik.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = batik.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvBatik) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = batikAdapter
            }

            binding.btnSearch.setOnClickListener {
                if(binding.etSearch.text.isEmpty()) {
                    binding.etSearch.setError("The search field cannot be empty");
                } else {
                    val intent = Intent(activity, SearchActivity::class.java)
                    val searchQuery = "%${binding.etSearch.text}%"
                    intent.putExtra(SearchActivity.SEARCH_QUERY, searchQuery)
                    startActivity(intent)
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}