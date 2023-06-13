package com.example.retripapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.retripapp.MainActivity
import com.example.retripapp.R
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val stat = FirebaseAuth.getInstance().currentUser != null
        var intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
        if (stat) {
            intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
        }
        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, 2000)
    }
}