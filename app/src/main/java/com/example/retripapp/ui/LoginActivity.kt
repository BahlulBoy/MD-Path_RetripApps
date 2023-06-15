package com.example.retripapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retripapp.MainActivity
import com.example.retripapp.databinding.ActivityLoginBinding
import com.example.retripapp.ui.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    private lateinit var loginViewModel : LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.registerTeleport.setOnClickListener {
            registerOnClick()
        }
        binding.buttonLogin.setOnClickListener {
            val email = binding.emailLogin.editText?.text
            val password = binding.passwordLogin.editText?.text
            val regexEmail = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
            val regexpass = Regex(".{8,}")
            if (!regexEmail.matches(email.toString()) || !regexpass.matches(password.toString())) {
                if (!regexEmail.matches(email.toString())) {
                    binding.emailLogin.editText?.error = "your email character is not valid"
                } else if (!regexpass.matches(password.toString())) {
                    binding.passwordLogin.editText?.error = "your password character is not valid"
                }
            } else {
                loginViewModel.signIn(email, password)
            }

        }
        binding.buttonWithoutLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
        loginViewModel.loginResult.observe(this, Observer { result ->
                result?.let {
                    if (it.success) {
                        Toast.makeText(this, "your email is ${it.user?.email}", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        if (it.error is FirebaseAuthInvalidUserException || it.error is FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(this, "Your email or password is incorrect", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this, "There is something wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        })
    }
    @Suppress("DEPRECATION")
    private fun registerOnClick () {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivityForResult(intent, REQ_REGISTER)
    }
    fun signIn(email: Editable?, password: Editable?, context: Context) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "your email is ${it.result.user?.email}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    if (it.exception is FirebaseAuthInvalidUserException || it.exception is FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(context, "Your email or password is incorrect", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "There is something wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
    companion object{
        const val REQ_REGISTER = 1
    }
}
