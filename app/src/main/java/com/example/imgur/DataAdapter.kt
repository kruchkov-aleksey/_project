package com.example.imgur

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imgur.entity.ImgurImage
import kotlinx.android.synthetic.main.image_list.view.*

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ImgurImage>() {
    override fun areItemsTheSame(oldItem: ImgurImage, newItem: ImgurImage): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImgurImage, newItem: ImgurImage): Boolean {
        return oldItem.id == newItem.id
    }

}

class DataAdapter(val listener: RecyclerViewClickListener) :
    ListAdapter<ImgurImage, DataAdapter.ImgurImageViewHolder>(DIFF_CALLBACK) {

    inner class ImgurImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ImgurImage?) {
            Glide.with(itemView.context).load(item?.images?.firstOrNull()?.link)
                .into(itemView.imageView)
            itemView.setOnClickListener { listener.onClick(currentList[adapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgurImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_list, parent, false)
        return ImgurImageViewHolder(view)
    }

    interface RecyclerViewClickListener {
        fun onClick(item: ImgurImage?)
    }

    override fun onBindViewHolder(holder: ImgurImageViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}