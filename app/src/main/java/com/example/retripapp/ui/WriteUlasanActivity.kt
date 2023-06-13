package com.example.retripapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.retripapp.R
import com.example.retripapp.databinding.ActivityWriteUlasanBinding
import com.example.retripapp.firebase.firebaseAuth
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

class WriteUlasanActivity : AppCompatActivity() {
    lateinit var binding : ActivityWriteUlasanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteUlasanBinding.inflate(layoutInflater)
        val id = intent.getStringExtra(ID_USER)
        val idUser = FirebaseAuth.getInstance().currentUser?.uid
        val firestore = FirebaseFirestore.getInstance()
        setContentView(binding.root)
        binding.cancelBtn.setOnClickListener(myClick)
        binding.extendedFabUlasan.setOnClickListener {
            var textUlasan = binding.textField.editText?.text.toString()
            var rating = (binding.ratingBar2.rating * 10).roundToInt().toFloat() / 10
            val dateNow = Timestamp.now()
            firestore.collection("account")
                .document("$idUser")
                .get().addOnSuccessListener {snapshot->
                val nama = snapshot.getString("name")
                val data = hashMapOf(
                    "nama" to "$nama",
                    "ulasan" to "$textUlasan",
                    "rating" to "$rating",
                    "date" to dateNow
                )
                firestore.collection("destinasi").document("$id").collection("review").add(data)
                    .addOnSuccessListener {
                        val intent = Intent()
                        setResult(DetailActivity.REQ_CODE_ULASAN, intent)
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "There is something wrong", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
    private val myClick = View.OnClickListener {
        when (it.id) {
            R.id.cancel_btn -> {
                onBackPressed()
            }
        }
    }
    companion object {
        val ID_USER = "id_user"
    }
}