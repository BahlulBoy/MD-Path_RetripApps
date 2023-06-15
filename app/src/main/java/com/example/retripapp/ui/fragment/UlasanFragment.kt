package com.example.retripapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retripapp.R
import com.example.retripapp.adapter.UlasanRcAdapter
import com.example.retripapp.data.Ulasan
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import java.text.SimpleDateFormat
import java.util.Locale

class UlasanFragment(val id : String) : Fragment() {
    private lateinit var recycleV : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ulasan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleV = view.findViewById(R.id.rc_ulasan)
        recycleV.layoutManager = LinearLayoutManager(requireContext())
        FirebaseFirestore.getInstance().collection("destinasi").document(id).collection("review").orderBy("date", Query.Direction.DESCENDING).get()
            .addOnSuccessListener {snapshot->
                val listUlasan = mutableListOf<Ulasan>()
                for (doc in snapshot) {
                    val date = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(doc.getTimestamp("date")?.toDate())
                    val temp = Ulasan(
                        id = doc.id,
                        name = doc.getString("nama"),
                        rating = doc.getString("rating")?.toFloat(),
                        ulasan = doc.getString("ulasan"),
                        date = date
                    )
                    listUlasan.add(temp)
                }
                val adapter = UlasanRcAdapter(listUlasan)
                recycleV.adapter = adapter
            }
    }
}