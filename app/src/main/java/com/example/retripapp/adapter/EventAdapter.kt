package com.example.retripapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retripapp.databinding.ImgEventItemBinding

class EventAdapter(val list: List<String>): RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    class ViewHolder(val itemsView: ImgEventItemBinding): RecyclerView.ViewHolder(itemsView.root){
        private val binding = itemsView
        fun bind(data: String) {
            with(binding){
                Glide.with(itemView)
                    .load(data)
                    .into(itemsView.imgnya)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ImgEventItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}