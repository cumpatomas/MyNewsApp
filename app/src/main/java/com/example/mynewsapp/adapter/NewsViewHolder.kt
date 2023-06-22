package com.example.mynewsapp.adapter

import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.mynewsapp.R
import com.example.mynewsapp.data.model.NewModel
import com.example.mynewsapp.databinding.NewItemBinding

class NewsViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = NewItemBinding.bind(view)

    fun display(new: NewModel, onClickListener: (String) -> Unit) {
        binding.tvItemName.text = new.title
        binding.tvDate.text = new.date.substringBefore('T')
        binding.tvSource.text = new.source
        Glide.with(binding.ivItemImage.context)
            .load(new.photo)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(15)))
            .into(binding.ivItemImage)

        itemView.setOnClickListener { onClickListener(new.link) }
        setAnimation()
    }
    private fun setAnimation() {
        val animation = AnimationUtils.loadAnimation(binding.root.context, R.anim.slide_in_left)
        itemView.startAnimation(animation)
    }
}