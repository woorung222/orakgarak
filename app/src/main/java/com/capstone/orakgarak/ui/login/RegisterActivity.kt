package com.capstone.orakgarak.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.orakgarak.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerUsername: EditText
    private lateinit var registerEmail: EditText
    private lateinit var registerPassword: EditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerUsername = findViewById(R.id.registerUsername)
        registerEmail = findViewById(R.id.registerEmail)
        registerPassword = findViewById(R.id.registerPassword)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val username = registerUsername.text.toString()
            val email = registerEmail.text.toString()
            val password = registerPassword.text.toString()
            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                saveUserData(username, email, password)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveUserData(username: String, email: String, password: String) {
        val sharedPref = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("${username}_email", email)
            putString("${username}_password", password)
            apply()
        }
        Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
}
