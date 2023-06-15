package com.example.retripapp.data

import com.google.firebase.auth.FirebaseUser

data class account(
    val name : String,
    val email : String
)
data class loginResult(
    val success : Boolean,
    val user : FirebaseUser? = null,
    val error : Exception? = null
)

data class registerResult(
    val success: Boolean,
    val error : Exception? = null
)