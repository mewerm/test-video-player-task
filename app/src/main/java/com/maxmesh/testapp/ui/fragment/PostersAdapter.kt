package com.maxmesh.testapp.ui.fragment

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maxmesh.testapp.R
import com.maxmesh.testapp.databinding.ItemPosterBinding
import com.maxmesh.testapp.domain.entity.MultimediaEntity

class PostersAdapter :
    ListAdapter<MultimediaEntity, PostersAdapter.PosterHolder>(Comparator()) {
    var onItemClicked: ((MultimediaEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPosterBinding.inflate(inflater, parent, false)
        return PosterHolder(binding, onItemClicked!!)
    }

    override fun onBindViewHolder(holder: PosterHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PosterHolder(
        private val binding: ItemPosterBinding,
        private val onItemClicked: (MultimediaEntity) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MultimediaEntity) {
            doBlurEffect(PorterDuff.Mode.DST)
            with(binding) {
                Glide.with(itemView).load(data.small_thumbnail_url)
                    .into(imageViewPoster)
                imageViewPoster.setOnClickListener {
                    onItemClicked.invoke(data)
                    if (adapterPosition == position) {
                        notifyItemChanged(position)
                        doBlurEffect(PorterDuff.Mode.MULTIPLY)
                    } else return@setOnClickListener
                }
            }
        }

        private fun doBlurEffect(mode: PorterDuff.Mode) {
            binding.imageViewPoster.setColorFilter(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.blur
                ), mode
            )
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
