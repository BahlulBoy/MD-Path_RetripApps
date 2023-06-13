package com.example.retripapp.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.retripapp.R
import com.example.retripapp.ui.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AccountAlreadyLogin : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_already_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val logout = view.findViewById<ConstraintLayout>(R.id.logout)
        val mAuth = FirebaseAuth.getInstance()
        val email = view.findViewById<TextView>(R.id.email_account)
        val username = view.findViewById<TextView>(R.id.username_account)
        if (mAuth.currentUser != null) {
            email.text = mAuth.currentUser?.email
            FirebaseFirestore.getInstance().collection("account").document("${mAuth.currentUser?.uid}").get()
                .addOnSuccessListener { data ->
                    username.text = data.getString("name")
                }
        }
        logout.setOnClickListener {
            alertDialog(requireContext())
        }
    }
    private fun alertDialog(context : Context) {
        android.app.AlertDialog.Builder(context)
            .setTitle("Logout Confirmation")
            .setMessage("are you sure to Log out")
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("Logout") { dialog, _ ->
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
                dialog.dismiss()
            }
            .create().show()
    }
}