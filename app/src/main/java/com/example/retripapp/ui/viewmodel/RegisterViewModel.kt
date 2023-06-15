package com.example.retripapp.ui.viewmodel

import android.content.Context
import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retripapp.data.registerResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModel: ViewModel() {
    private val _registerResult = MutableLiveData<registerResult>()
    val registerResult: LiveData<registerResult>
        get() = _registerResult
    fun creatUserFirebase(context: Context, username : Editable?, email : Editable?, password : Editable?) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.toString(), password.toString())
            .addOnCompleteListener {task ->
                if (task.isSuccessful) {
                    val email = task.result.user?.email
                    val uid = task.result.user?.uid
                    val username = username.toString()
                    FirebaseFirestore.getInstance().collection("account").document("$uid")
                        .set(hashMapOf(
                                "name" to username,
                                "email" to email
                            ))
                        .addOnCompleteListener {task->
                            if (task.isSuccessful){
                                _registerResult.value = registerResult(success = true)
                            } else {
                                _registerResult.value = registerResult(false, task.exception)
                            }
                        }
                } else {
                    _registerResult.value = registerResult(success = false, error = task.exception)
                }
            }
    }
}