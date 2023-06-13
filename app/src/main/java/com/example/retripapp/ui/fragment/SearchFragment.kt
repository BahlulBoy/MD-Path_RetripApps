package com.example.retripapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retripapp.R
import com.example.retripapp.adapter.SearchRvAdapter
import com.example.retripapp.data.Destinasi
import com.google.firebase.firestore.FirebaseFirestore

class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val search = view.findViewById<SearchView>(R.id.search_des)
        val rvAnswer = view.findViewById<RecyclerView>(R.id.rv_search)
        rvAnswer.layoutManager = LinearLayoutManager(requireContext())
        search.queryHint = "Temukan Tempat"
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                FirebaseFirestore.getInstance().collection("destinasi")
                    .whereEqualTo("nama", "$query").get()
                    .addOnSuccessListener { snapshot->
                    val data = mutableListOf<Destinasi>()
                    for (doc in snapshot) {
                        val ds = Destinasi(
                            id = doc.id,
                            nama = doc.getString("nama"),
                            lokasi = doc.getString("kota"),
                            category = doc.getString("category"),
                            deskripsi = doc.getString("deskripsi"),
                            img = doc.getString("img"),
                            lat = doc.getString("lat"),
                            lang = doc.getString("lang")
                        )
                        data.add(ds)
                    }
                    rvAnswer.adapter = SearchRvAdapter(data)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

}