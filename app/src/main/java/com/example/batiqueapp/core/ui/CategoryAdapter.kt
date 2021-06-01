package com.example.batiqueapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.batiqueapp.R
import com.example.batiqueapp.core.domain.model.Category
import com.example.batiqueapp.databinding.ItemCard3Binding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ListViewHolder>() {

    private var listData = ArrayList<Category>()

    var onItemClick: ((Category) -> Unit)? = null

    fun setData(newListData: List<Category>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCard3Binding.bind(itemView)
        fun bind(data: Category) {
            with(binding) {
                tvTitle.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder = ListViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_card3, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

}