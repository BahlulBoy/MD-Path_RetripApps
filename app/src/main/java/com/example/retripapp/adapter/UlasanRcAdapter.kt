package com.example.retripapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retripapp.R
import com.example.retripapp.data.Ulasan

class UlasanRcAdapter(private val ulasan: List<Ulasan>) : RecyclerView.Adapter<UlasanRcAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_card_review, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = ulasan.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var item = ulasan[position]
        holder.name.text = item.name
        holder.rating.rating = item.rating!!
        holder.tanggal.text = item.date
        holder.textUlasan.text = item.ulasan
    }
    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nama_ulasan)
        val rating : RatingBar = itemView.findViewById(R.id.ratingBar)
        val textUlasan : TextView = itemView.findViewById(R.id.deskripsi_ulasan)
        val tanggal : TextView = itemView.findViewById(R.id.date)
    }
}