package com.example.firebase_auth

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    private lateinit var welcomeTextView: TextView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        welcomeTextView = findViewById(R.id.welcomeTextView)
        mAuth = FirebaseAuth.getInstance()

        val user: FirebaseUser? = mAuth.currentUser
        if (user != null) {
            welcomeTextView.text = getString(R.string.welcome, user.email)
            //change 1
        }
    }
}