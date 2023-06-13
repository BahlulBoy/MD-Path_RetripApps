package com.example.retripapp.ui.fragment

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.retripapp.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment(private val cordinate : LatLng?, private val isGestureEnable : Boolean) : Fragment() {
    private val callback = OnMapReadyCallback { gMap ->
        val cor = cordinate ?: LatLng(-34.0, 151.0)
        gMap.uiSettings.setAllGesturesEnabled(isGestureEnable)
        gMap.uiSettings.isZoomGesturesEnabled = isGestureEnable

        gMap.addMarker(MarkerOptions().position(cor).title("Destinasi"))
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cor, 10f))
        gMap.moveCamera(CameraUpdateFactory.newLatLng(cor))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}