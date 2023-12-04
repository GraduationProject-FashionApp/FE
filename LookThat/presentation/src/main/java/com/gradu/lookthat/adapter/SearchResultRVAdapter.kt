package com.gradu.lookthat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gradu.lookthat.databinding.ItemFragmentClosetClothingBinding
import com.gradu.lookthat.databinding.ItemFragmentSearchResultBinding

class SearchResultRVAdapter(private val itemList: ArrayList<String>) : RecyclerView.Adapter<SearchResultRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemFragmentSearchResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFragmentSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size
}