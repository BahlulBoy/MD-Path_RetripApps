package com.example.retripapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retripapp.data.Destinasi
import com.example.retripapp.databinding.ItemPopularCardBinding
import com.example.retripapp.ui.DetailActivity
import com.example.retripapp.ui.fragment.BerandaFragment

class PopularRcAdapter(val listItem : List<Destinasi>):
    RecyclerView.Adapter<PopularRcAdapter.HolderClass>() {
    class HolderClass(val items: ItemPopularCardBinding, val context: Context): RecyclerView.ViewHolder(items.root) {
        private val binding = items
        fun bind(itemData : Destinasi) {
            Glide.with(itemView).load(itemData.img).into(binding.imageView5)
            with(binding) {
                titleDestination.text = itemData.nama
                lokasiTitle.text = itemData.lokasi
            }
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DESTINASI, itemData.id)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderClass {
        return HolderClass(ItemPopularCardBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: HolderClass, position: Int) {
        holder.bind(listItem[position])
    }
}