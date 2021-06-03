package com.example.batiqueapp.history

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batiqueapp.core.ui.ArchiveAdapter
import com.example.batiqueapp.core.ui.ViewModelFactory
import com.example.batiqueapp.databinding.FragmentHistoryBinding
import com.example.batiqueapp.detail.DetailActivity

class HistoryFragment : Fragment() {

    private lateinit var historyViewModel: HistoryViewModel

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val archiveAdapter = ArchiveAdapter()
            archiveAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            historyViewModel = ViewModelProvider(this, factory)[HistoryViewModel::class.java]

            historyViewModel.latestAccess.observe(viewLifecycleOwner, { dataBatik ->
                archiveAdapter.setData(dataBatik)
                binding.viewEmpty.root.visibility = if (dataBatik.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvBatik) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = archiveAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}