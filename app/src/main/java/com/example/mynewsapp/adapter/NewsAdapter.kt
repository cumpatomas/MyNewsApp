package com.example.mynewsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsapp.R
import com.example.mynewsapp.data.model.NewModel

class NewsAdapter: RecyclerView.Adapter<NewsViewHolder>() {
    private var list = mutableListOf<NewModel>()

    var onItemClickListener: (String) -> Unit = {}

    fun setList(list: List<NewModel>) {
        this.list = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(layoutInflater.inflate(R.layout.new_item, parent, false))
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = list[position]
        holder.display(item, onItemClickListener)
    }

    override fun getItemCount(): Int = list.size
}