package com.example.retripapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retripapp.data.Destinasi
import com.example.retripapp.databinding.ItemCardSearchBinding
import com.example.retripapp.ui.DetailActivity

class SearchRvAdapter(var list: List<Destinasi>): RecyclerView.Adapter<SearchRvAdapter.HolderClass>() {
    class HolderClass(val item: ItemCardSearchBinding, val context : Context): RecyclerView.ViewHolder(item.root) {
        val binding = item
        fun bind(data:Destinasi) {
            Glide.with(itemView)
                .load(data.img)
                .into(binding.imageView2)
            binding.judul.text = "${data.nama}"
            binding.lokasi.text = "${data.lokasi}"
            binding.ulasan.text = ""
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DESTINASI, data.id)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderClass {
        return HolderClass(ItemCardSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HolderClass, position: Int) {
       holder.bind(list[position])
    }
}