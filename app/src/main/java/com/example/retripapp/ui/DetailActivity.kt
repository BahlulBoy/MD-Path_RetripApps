package com.example.retripapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.retripapp.R
import com.example.retripapp.adapter.DetailTabsAdapter
import com.example.retripapp.data.Destinasi
import com.example.retripapp.databinding.ActivityDetailBinding
import com.example.retripapp.ui.fragment.LokasiFragment
import com.example.retripapp.ui.fragment.TentangFragment
import com.example.retripapp.ui.fragment.UlasanFragment
import com.example.retripapp.ui.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewPagerAdapter: DetailTabsAdapter
    private lateinit var destinasiData: String
    private lateinit var detailViewModel: DetailViewModel
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        destinasiData = "${intent.getStringExtra(DESTINASI)}"
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        detailViewModel.destinasi.observe(this) { destinasi ->
            destinasi?.let {
                showData(it)
            }
        }
        detailViewModel.loadData(destinasiData)

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

    private fun showData(destinasi: Destinasi) {
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