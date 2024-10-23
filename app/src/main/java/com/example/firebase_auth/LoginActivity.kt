package com.example.firebase_auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button
    private lateinit var signupTextView: TextView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        mAuth = FirebaseAuth.getInstance()


        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.Login)
        signupTextView = findViewById(R.id.btn_createAcc)


        loginButton.setOnClickListener {
            loginUser()
        }


        signupTextView.setOnClickListener {

            startActivity(Intent(this, CreateAccountActivity::class.java))
        }
    }

    private fun loginUser() {

        val username = usernameInput.text.toString()
        val password = passwordInput.text.toString()


        if (username.isEmpty() || password.isEmpty()) {

            showToast("All fields are required")
            return
        }


        mAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user: FirebaseUser? = mAuth.currentUser
                    showToast("Login successful")
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {

                    showToast("Login failed: ${task.exception?.message}")
                }
            }
    }


    private fun showToast(message: String) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }
}