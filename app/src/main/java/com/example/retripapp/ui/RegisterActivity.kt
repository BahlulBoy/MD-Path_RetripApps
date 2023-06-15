package com.example.retripapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.retripapp.data.account
import com.example.retripapp.databinding.ActivityRegisterBinding
import com.example.retripapp.firebase.dbFirebase
import com.example.retripapp.ui.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        firebaseAuth = FirebaseAuth.getInstance()

        val email = binding.emailRegister.editText
        val password = binding.passwordRegister.editText
        val username = binding.usernameRegister.editText

        binding.backButtonRegister.setOnClickListener {
            onBackPressed()
        }
        binding.buttonRegister.setOnClickListener {
            if (email?.text.toString() != "" && password?.text.toString() != "") {
                registerViewModel.creatUserFirebase(this, username?.text, email?.text, password?.text)
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
        registerViewModel.registerResult.observe(this) { result ->
            if (result.success) {
                Toast.makeText(this, "Account is Created", Toast.LENGTH_SHORT).show()
                val intent = Intent()
                setResult(LoginActivity.REQ_REGISTER, intent)
                finish()
            } else {
                Toast.makeText(this, "Sorry there is a mistake ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createUserFirebase(context: Context, email: Editable?, password: Editable?, username: Editable?) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener {task ->
                if (task.isComplete) {
                    val emailnya = task.result.user?.email.toString()
                    val uid = task.result.user?.uid
                    val data = account(username.toString(), email.toString())
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