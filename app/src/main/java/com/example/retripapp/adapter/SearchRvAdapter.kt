package com.example.retripapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retripapp.data.Destinasi
import com.example.retripapp.databinding.ItemCardSearchBinding

class SearchRvAdapter(var list: List<Destinasi>): RecyclerView.Adapter<SearchRvAdapter.HolderClass>() {
    class HolderClass(val item: ItemCardSearchBinding): RecyclerView.ViewHolder(item.root) {
        val binding = item
        fun bind(data:Destinasi) {
            Glide.with(itemView)
                .load(data.img)
                .into(binding.imageView2)
            binding.judul.text = "${data.nama}"
            binding.lokasi.text = "${data.lokasi}"
            binding.ulasan.text = ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderClass {
        return HolderClass(ItemCardSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HolderClass, position: Int) {
       holder.bind(list[position])
    }
}