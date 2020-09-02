package com.example.photoslibrary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photoslibrary.R
import com.example.photoslibrary.model.Photo
import kotlinx.android.synthetic.main.item.view.*

class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
           return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
       val photo = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(photo.urls.small).into(ivImage)
            ivTitle.text = photo.description

            setOnClickListener {
                onItemClickListener?.let { it(photo) }
            }
        }
    }

    private var onItemClickListener: ((Photo) -> Unit)? = null

    fun setOnItemClickListener(listener: (Photo) -> Unit) {
        onItemClickListener = listener
    }
}