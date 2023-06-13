package com.example.retripapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.retripapp.databinding.ActivityMainBinding
import com.example.retripapp.ui.fragment.AccountAlreadyLogin
import com.example.retripapp.ui.fragment.AccountFragment
import com.example.retripapp.ui.fragment.AccountNoLogin
import com.example.retripapp.ui.fragment.BerandaFragment
import com.example.retripapp.ui.fragment.SearchFragment
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(BerandaFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_nav -> {
                    changeFragment(BerandaFragment())
                    true
                }
                R.id.search_nav -> {
                    changeFragment(SearchFragment())
                    true
                }
                R.id.account_nav -> {
                    var stat = FirebaseAuth.getInstance().currentUser != null
                    if (stat) {
                        changeFragment(AccountFragment(AccountAlreadyLogin()))
                    } else {
                        changeFragment(AccountFragment(AccountNoLogin()))
                    }
                    true
                }
                else -> {
                    false
                }
            }
        }
        //var intent = Intent(this@MainActivity, DetailActivity::class.java)
        //intent.putExtra(DetailActivity.DESTINASI, borobudur)
        //startActivity(intent)

    }
    private fun changeFragment(frag : Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_frag, frag)
            .commit()
    }
}