package com.example.retripapp.ui.viewmodel

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retripapp.data.loginResult
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {
    private val _loginResult = MutableLiveData<loginResult>()
    val loginResult : LiveData<loginResult>
        get() = _loginResult

    fun signIn(email : Editable?, password : Editable?) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener {task ->
                if (task.isComplete){
                    val user = task.result?.user
                    _loginResult.value = loginResult(true, user = user)
                } else {
                    _loginResult.value = loginResult(false, null, error = task.exception)
                }
            }
    }
}

