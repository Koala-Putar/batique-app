package com.example.batiqueapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.batiqueapp.R
import com.example.batiqueapp.core.domain.model.Batik
import com.example.batiqueapp.databinding.ItemCard2Binding

class ArchiveAdapter : RecyclerView.Adapter<ArchiveAdapter.ListViewHolder>() {

    private var listData = ArrayList<Batik>()

    var onItemClick: ((Batik) -> Unit)? = null

    fun setData(newListData: List<Batik>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCard2Binding.bind(itemView)
        fun bind(data: Batik) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivPoster)
                tvTitle.text = data.name
                tvCategory.text = data.category
                tvDescription.text = data.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card2, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

}