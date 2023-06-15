package com.example.retripapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retripapp.data.Destinasi
import com.google.firebase.firestore.FirebaseFirestore

class DetailViewModel: ViewModel() {
    private val _destinasi = MutableLiveData<Destinasi>()
    val destinasi: LiveData<Destinasi>
        get() = _destinasi

    fun loadData(destinasiData: String) {
        FirebaseFirestore.getInstance().collection("destinasi").document("$destinasiData")
            .get()
            .addOnSuccessListener {snapshot->
                val destinasi = Destinasi(
                    id = snapshot.id,
                    nama = snapshot.getString("nama"),
                    lokasi = snapshot.getString("kota"),
                    category = snapshot.getString("category"),
                    deskripsi = snapshot.getString("deskripsi"),
                    img = snapshot.getString("img"),
                    lat = snapshot.getString("lat"),
                    lang = snapshot.getString("lang")
                )
                _destinasi.value = destinasi
            }
    }
}