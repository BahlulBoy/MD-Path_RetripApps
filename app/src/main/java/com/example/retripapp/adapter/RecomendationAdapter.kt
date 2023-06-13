package com.example.retripapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retripapp.data.Destinasi
import com.example.retripapp.databinding.RecomendationCardItemBinding
import com.example.retripapp.ui.DetailActivity

class RecomendationAdapter(var list: List<Destinasi>): RecyclerView.Adapter<RecomendationAdapter.HolderClass>() {
    class HolderClass(val item : RecomendationCardItemBinding, val context : Context): RecyclerView.ViewHolder(item.root){
        private val binding = item
        fun bind(data : Destinasi) {
            Glide.with(itemView).load(data.img).into(binding.imgRecommend)
            with(binding) {
                titleRecomend.text = data.nama
                lokasiRecomend.text = data.lokasi
            }
            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.DESTINASI, data.id)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderClass {
        return HolderClass(RecomendationCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), parent.context)
    }

    override fun onBindViewHolder(holder: HolderClass, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}