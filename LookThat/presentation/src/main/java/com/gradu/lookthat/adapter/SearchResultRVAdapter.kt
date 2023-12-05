package com.gradu.lookthat.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gradu.lookthat.databinding.ItemFragmentClosetClothingBinding
import com.gradu.lookthat.databinding.ItemFragmentSearchResultBinding
import com.gradu.lookthat.views.search.SearchProductDetailActivity

class SearchResultRVAdapter(private val itemList: ArrayList<String>) : RecyclerView.Adapter<SearchResultRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick()
    }

    private lateinit var myItemClickListener : MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener){
        myItemClickListener = itemClickListener
    }

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
        holder.itemView.setOnClickListener {
            Log.d("SearchResultRVAdapter","onBindViewHolder : ItemClick")
            myItemClickListener.onItemClick()
        }
    }

    override fun getItemCount() = itemList.size
}