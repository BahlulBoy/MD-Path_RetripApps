package com.example.retripapp.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

val firebaseAuth = FirebaseAuth.getInstance()
val dbFirebase = Firebase.firestore