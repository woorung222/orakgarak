package com.capstone.orakgarak.ui.login

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.capstone.orakgarak.R
import com.capstone.orakgarak.ui.facility.FacilitySelectionActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button
    private lateinit var loadingProgressBar: ProgressBar

    private val LOCATION_PERMISSION_REQUEST_CODE = 1000

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
            requestLocationPermission()
        } else {
            Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            // 권한이 이미 부여된 경우
            proceedToNextActivity()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                proceedToNextActivity()
            } else {
                Toast.makeText(this, "위치 권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
                // 권한이 거부되었을 때의 처리
                proceedToNextActivity() // 권한이 거부되었을 때도 다음 화면으로 이동
            }
        }
    }

    private fun proceedToNextActivity() {
        startActivity(Intent(this, FacilitySelectionActivity::class.java))
        finish()
    }
}
