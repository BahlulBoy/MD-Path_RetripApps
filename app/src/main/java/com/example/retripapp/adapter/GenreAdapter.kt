package com.example.retripapp.adapter

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.retripapp.data.Genre
import com.example.retripapp.databinding.ItemCardGenreBinding
import com.example.retripapp.fungsi.NotImplemented

class GenreAdapter(private val items: List<Genre>) :  RecyclerView.Adapter<GenreAdapter.ImageViewHolder>(){
    class ImageViewHolder(itemsView : ItemCardGenreBinding) : RecyclerView.ViewHolder(itemsView.root) {
        private val binding = itemsView
        fun bind(data : Genre) {
            with(binding) {
                Glide.with(itemView)
                    .load(data.img)
                    .into(imageView4)
                textView5.text = data.text
            }
            itemView.setOnClickListener {
                NotImplemented(itemView.context)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ItemCardGenreBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(items[position])
    }
}