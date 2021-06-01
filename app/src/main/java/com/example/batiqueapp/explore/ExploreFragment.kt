package com.example.batiqueapp.explore

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.batiqueapp.R
import com.example.batiqueapp.archive.ArchiveActivity
import com.example.batiqueapp.core.data.Resource
import com.example.batiqueapp.core.ui.CategoryAdapter
import com.example.batiqueapp.core.ui.ViewModelFactory
import com.example.batiqueapp.databinding.FragmentExploreBinding

class ExploreFragment : Fragment() {

    private lateinit var exploreViewModel: ExploreViewModel

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val categoryActivity = CategoryAdapter()
            categoryActivity.onItemClick = { selectedData ->
                val intent = Intent(activity, ArchiveActivity::class.java)
                intent.putExtra(ArchiveActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            exploreViewModel = ViewModelProvider(this, factory)[ExploreViewModel::class.java]

            exploreViewModel.category.observe(viewLifecycleOwner, { category ->
                if (category != null) {
                    when (category) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            categoryActivity.setData(category.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = category.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvBatik) {
                layoutManager = GridLayoutManager(context, 2)
                setHasFixedSize(true)
                adapter = categoryActivity
            }
        }

        Toast.makeText(context, "Welcome to Explore", Toast.LENGTH_SHORT).show()
    }

}