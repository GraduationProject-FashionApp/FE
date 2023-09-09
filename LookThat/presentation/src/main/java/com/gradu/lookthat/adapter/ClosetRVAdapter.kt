package com.gradu.lookthat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gradu.lookthat.databinding.ItemFragmentClosetClothingBinding

class ClosetRVAdapter(private val itemList: ArrayList<String>) : RecyclerView.Adapter<ClosetRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemFragmentClosetClothingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFragmentClosetClothingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size
}