package com.gradu.lookthat.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gradu.lookthat.databinding.ItemFragmentSearchResultBinding
import com.gradu.lookthat.views.SearchResultSimilarFragment.Companion.DISCOUNT_PRICE
import com.gradu.lookthat.views.SearchResultSimilarFragment.Companion.TITLE
import com.gradu.lookthat.views.SearchResultSimilarFragment.Companion.URL
import com.gradu.lookthat.views.api.Item

class SearchResultRVAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<SearchResultRVAdapter.ViewHolder>() {

    interface MyItemClickListener{
        fun onItemClick(itemList: List<Item>, position: Int)
    }

    private lateinit var myItemClickListener : MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener){
        myItemClickListener = itemClickListener
    }

    inner class ViewHolder(private val binding: ItemFragmentSearchResultBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            with(binding) {
                Glide.with(itemSearchResultIv)
                    .load(item.data[URL]).into(itemSearchResultIv)
                itemSearchResultTitleTxt.text = item.data[TITLE]
                var price = item.data[DISCOUNT_PRICE]
                price = price.substring(0,price.length-3) + "," + price.substring(price.length-3) + "원"
                itemSearchResultPriceTxt.text = price
            }
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
            myItemClickListener.onItemClick(itemList, position)
        }
    }

    override fun getItemCount() = itemList.size
}