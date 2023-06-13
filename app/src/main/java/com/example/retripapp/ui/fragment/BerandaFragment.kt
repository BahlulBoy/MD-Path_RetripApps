package com.example.retripapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.retripapp.R
import com.example.retripapp.adapter.EventAdapter
import com.example.retripapp.adapter.GenreAdapter
import com.example.retripapp.adapter.PopularRcAdapter
import com.example.retripapp.adapter.RecomendationAdapter
import com.example.retripapp.data.Destinasi
import com.example.retripapp.data.event
import com.example.retripapp.data.genreList
import com.google.firebase.firestore.FirebaseFirestore

class BerandaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beranda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbFirebase = FirebaseFirestore.getInstance().collection("destinasi").limit(4L)
        val dbFirebaseAll = FirebaseFirestore.getInstance().collection("destinasi")
        val rcGenre = view.findViewById<RecyclerView>(R.id.rc_genre)
        val rcEvent = view.findViewById<ViewPager2>(R.id.slider_img_event)

        val rcRecomendation = view.findViewById<RecyclerView>(R.id.recomendation_rc)
        rcGenre.layoutManager = GridLayoutManager(requireContext(), 4)
        val layoutM = LinearLayoutManager(requireContext())
        layoutM.isAutoMeasureEnabled = true
        rcRecomendation.layoutManager = layoutM
        rcGenre.adapter = GenreAdapter(genreList)
        rcEvent.adapter = EventAdapter(event)
        val rcPopular = view.findViewById<RecyclerView>(R.id.rc_popular)
        rcPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        dbFirebase.get().addOnSuccessListener { snapshot ->
            val destinasi = mutableListOf<Destinasi>()
            for (doc in snapshot) {
                val des = Destinasi(
                    id = doc.id,
                    nama = doc.getString("nama"),
                    lokasi = doc.getString("kota"),
                    category = doc.getString("category"),
                    deskripsi = doc.getString("deskripsi"),
                    img = doc.getString("img"),
                    lat = doc.getString("lat"),
                    lang = doc.getString("lang")
                )
                destinasi.add(des)
            }
            rcPopular.adapter = PopularRcAdapter(destinasi)
        }
        dbFirebaseAll.get().addOnSuccessListener {snapAll->
            val des = mutableListOf<Destinasi>()
            for (doc in snapAll) {
                val destinasi = Destinasi(
                    id = doc.id,
                    nama = doc.getString("nama"),
                    lokasi = doc.getString("kota"),
                    category = doc.getString("category"),
                    deskripsi = doc.getString("deskripsi"),
                    img = doc.getString("img"),
                    lat = doc.getString("lat"),
                    lang = doc.getString("lang")
                )
                des.add(destinasi)
            }
            rcRecomendation.adapter = RecomendationAdapter(des)
        }
    }
}