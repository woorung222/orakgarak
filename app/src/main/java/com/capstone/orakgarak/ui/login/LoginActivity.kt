package com.capstone.orakgarak.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.orakgarak.R
import com.capstone.orakgarak.ui.facility.FacilitySelectionActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var loadingProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        emailEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.login)
        registerButton = findViewById(R.id.register)
        loadingProgressBar = findViewById(R.id.loading)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            loadingProgressBar.visibility = View.VISIBLE
            loginUser(email, password)
        }

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        val sharedPref = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val users = sharedPref.all.filterKeys { it.endsWith("_email") }
            .mapKeys { it.key.removeSuffix("_email") }

        var loginSuccess = false
        for ((username, storedEmail) in users) {
            val storedPassword = sharedPref.getString("${username}_password", null)
            if (storedEmail == email && storedPassword == password) {
                loginSuccess = true
                break
            }
        }

        loadingProgressBar.visibility = View.GONE

        if (loginSuccess) {
            startActivity(Intent(this@LoginActivity, FacilitySelectionActivity::class.java))
            finish()
        } else {
            Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
        }
    }
}
