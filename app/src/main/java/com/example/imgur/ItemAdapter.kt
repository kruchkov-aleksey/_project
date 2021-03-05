package com.example.imgur

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
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

class ItemAdapter(val listener: RecyclerViewClickListener) :
    PagedListAdapter<ImgurImage, ItemAdapter.ImgurImageViewHolder>(DIFF_CALLBACK) {

    private val set = ConstraintSet()

    inner class ImgurImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ImgurImage?) {
            Glide.with(itemView.context).load(item?.images?.firstOrNull()?.link)
                .into(itemView.imageView)
            val ratio = String.format("%d:%d", item?.images?.firstOrNull()?.width, item?.images?.firstOrNull()?.height)
            set.clone(itemView.parentConstraint)
            set.setDimensionRatio(itemView.imageView.id, ratio)
            set.applyTo(itemView.parentConstraint)
            itemView.setOnClickListener { listener.onClick(currentList?.get(adapterPosition)) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemAdapter.ImgurImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_list, parent, false)
        return ImgurImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ImgurImageViewHolder, position: Int) {
        holder.bind(currentList?.get(position))
    }

    interface RecyclerViewClickListener {
        fun onClick(item: ImgurImage?)
    }
}