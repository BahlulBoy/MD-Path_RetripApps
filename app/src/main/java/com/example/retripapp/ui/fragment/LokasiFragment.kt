package com.example.retripapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.retripapp.R
import com.google.android.gms.maps.model.LatLng

class LokasiFragment(var lat : String, var lang : String) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lokasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lokasi = LatLng(lat.toDouble(), lang.toDouble())
        addMapFrag(lokasi)
    }

    fun addMapFrag(kordinat : LatLng) {
        val mapFrag = MapsFragment(kordinat, true)
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.maps_lokasi, mapFrag, "childFragmentTag")
        transaction.addToBackStack(null)
        transaction.commit()

    }
}