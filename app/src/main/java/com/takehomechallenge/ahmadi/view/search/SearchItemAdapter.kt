package com.takehomechallenge.ahmadi.view.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.takehomechallenge.ahmadi.data.model.Character
import com.takehomechallenge.ahmadi.databinding.ItemCharacterBinding

class SearchItemAdapter(private val onItemClick: (data: Character, position: Int) -> Unit) :
    RecyclerView.Adapter<SearchItemAdapter.SearchItemViewHolder>() {
    private val dataset = mutableListOf<Character>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        return SearchItemViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        holder.createView(position)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun addDataSet(data: List<Character>) {
        val lastIndex = dataset.lastIndex
        dataset.addAll(data)
        notifyItemRangeInserted(lastIndex, data.size)
    }

    fun clearDataset() {
        dataset.clear()
        notifyDataSetChanged()
    }

    inner class SearchItemViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun createView(position: Int) {
            binding.data = dataset[position]
            binding.root.setOnClickListener { onItemClick(dataset[position], position) }
        }
    }
}