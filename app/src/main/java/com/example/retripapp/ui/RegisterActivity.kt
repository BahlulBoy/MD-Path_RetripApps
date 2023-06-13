package com.example.retripapp.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.example.retripapp.data.account
import com.example.retripapp.databinding.ActivityRegisterBinding
import com.example.retripapp.firebase.dbFirebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        val email = binding.emailRegister.editText
        val password = binding.passwordRegister.editText
        val username = binding.usernameRegister.editText
        binding.backButtonRegister.setOnClickListener {
            onBackPressed()
        }
        binding.buttonRegister.setOnClickListener {
            if (email?.text.toString() != "" && password?.text.toString() != "") {
                createUserFirebase(this, email?.text, password?.text, username?.text)
            } else {
                if (username?.text.toString() == "") {
                    email?.error = "your email is empty"
                } else if (password?.text.toString() == "") {
                    password?.error = "your password is empty"
                } else if (email?.text.toString() == "") {
                    username?.error = "your username is empty"
                }
            }
        }
    }

    private fun createUserFirebase(context: Context, email: Editable?, password: Editable?, username: Editable?) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener {task ->
                if (task.isComplete) {
                    val emailnya = task.result.user?.email.toString()
                    val uid = task.result.user?.uid
                    var data = account(username.toString(), email.toString())
                    dbFirebase.collection("account").document("$uid")
                        .set(hashMapOf(
                            "name" to data.name,
                            "email" to data.email
                        ))
                        .addOnCompleteListener {
                            if (task.isComplete) {
                                Toast.makeText(context, "Account is Created", Toast.LENGTH_SHORT).show()
                                val intent = Intent()
                                setResult(LoginActivity.REQ_REGISTER, intent)
                                finish()
                            } else {
                                Toast.makeText(context, "Sorry there is a mistake ", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(context, "Sorry there is a mistake ", Toast.LENGTH_SHORT).show()
                }
            }
    }
}