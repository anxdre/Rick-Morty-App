package com.takehomechallenge.ahmadi.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.takehomechallenge.ahmadi.data.model.Character
import com.takehomechallenge.ahmadi.databinding.ItemCharacterBinding

class HomeItemAdapter(private val onItemClick: (data: Character, position: Int) -> Unit) :
    RecyclerView.Adapter<HomeItemAdapter.HomeItemViewHolder>() {
    private val dataset = mutableListOf<Character>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemViewHolder {
        return HomeItemViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeItemViewHolder, position: Int) {
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

    inner class HomeItemViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun createView(position: Int) {
            binding.data = dataset[position]
            binding.root.setOnClickListener { onItemClick(dataset[position], position) }
        }
    }
}