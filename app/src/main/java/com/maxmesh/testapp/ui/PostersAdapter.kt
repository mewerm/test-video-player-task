package com.maxmesh.testapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maxmesh.testapp.databinding.ItemPosterBinding
import com.maxmesh.testapp.domain.entity.MultimediaEntity

class PostersAdapter: ListAdapter<MultimediaEntity, PostersAdapter.PosterHolder>(Comparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPosterBinding.inflate(inflater, parent, false)
        return PosterHolder(binding)
    }

    override fun onBindViewHolder(holder: PosterHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PosterHolder(
        private val binding: ItemPosterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MultimediaEntity) {
            with(binding) {
                Glide.with(itemView).load(data.small_thumbnail_url)
                    .into(imageViewPoster)
            }
        }
    }
}

class Comparator :
    DiffUtil.ItemCallback<MultimediaEntity>() {
    override fun areItemsTheSame(oldItem: MultimediaEntity, newItem: MultimediaEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MultimediaEntity, newItem: MultimediaEntity): Boolean {
        return oldItem == newItem
    }
}
