package com.example.retripapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.retripapp.R
import com.example.retripapp.adapter.DetailTabsAdapter
import com.example.retripapp.data.Destinasi
import com.example.retripapp.databinding.ActivityDetailBinding
import com.example.retripapp.ui.fragment.LokasiFragment
import com.example.retripapp.ui.fragment.TentangFragment
import com.example.retripapp.ui.fragment.UlasanFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewPagerAdapter: DetailTabsAdapter
    private lateinit var destinasi: Destinasi
    private lateinit var destinasiData: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //set Data dari Intent ke Content
        destinasiData = "${intent.getStringExtra(DESTINASI)}"
        //set Data dari Intent ke Content

        getData(destinasiData)

        binding.backButton.setOnClickListener {
            onBackPressed()
        }
        binding.extendedFab.setOnClickListener {
            val firebaseAuthen = FirebaseAuth.getInstance()
            if (firebaseAuthen.currentUser != null) {
                val intent = Intent(this@DetailActivity, WriteUlasanActivity::class.java)
                intent.putExtra(WriteUlasanActivity.ID_USER, destinasiData)
                startActivityForResult(intent, REQ_CODE_ULASAN)
            } else {
                alertDialog(this)
                Toast.makeText(this, "you not login", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun getData(destinasiData : String) {
        FirebaseFirestore.getInstance().collection("destinasi").document("$destinasiData")
            .get().addOnSuccessListener {snapshot->
                destinasi = Destinasi(
                    id = snapshot.id,
                    nama = snapshot.getString("nama"),
                    lokasi = snapshot.getString("kota"),
                    category = snapshot.getString("category"),
                    deskripsi = snapshot.getString("deskripsi"),
                    img = snapshot.getString("img"),
                    lat = snapshot.getString("lat"),
                    lang = snapshot.getString("lang")
                )
                binding.destinasiName.text = destinasi.nama
                binding.destinasiLokasi.text = destinasi.lokasi
                Glide.with(this)
                    .load(destinasi.img)
                    .into(binding.imgDestinasi)
                viewPagerAdapter = DetailTabsAdapter(this)
                binding.vpFragment.adapter = viewPagerAdapter
                viewPagerAdapter.addFragment(TentangFragment("${destinasi.nama}", "${destinasi.deskripsi}"))
                viewPagerAdapter.addFragment(LokasiFragment("${destinasi.lat}", "${destinasi.lang}"))
                viewPagerAdapter.addFragment(UlasanFragment("${destinasi.id}"))
                TabLayoutMediator(binding.tabs, binding.vpFragment) { tab, position ->
                    tab.text = resources.getString(TAB_TITLES[position])
                }.attach()
            }
    }

    private fun alertDialog(context : Context) {
        android.app.AlertDialog.Builder(context)
            .setTitle("This action need login")
            .setMessage("this action need you to login. are you want to login?")
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Login") { dialog, _ ->
                val intent = Intent(context, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
                finish()
                dialog.dismiss()
            }
            .create().show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_ULASAN && resultCode== REQ_CODE_ULASAN) {

        }
    }

    override fun onResume() {
        getData(destinasiData)
        super.onResume()
    }
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2,
            R.string.tab_text_3
        )

        const val DESTINASI = "destinasi"
        const val REQ_CODE_ULASAN = 1
    }
}