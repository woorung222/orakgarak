package com.capstone.orakgarak.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.capstone.orakgarak.SplashActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, SplashActivity::class.java))
        finish()
    }
}
