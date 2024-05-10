package com.capstone.orakgarak.ui.facility

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.capstone.orakgarak.R

class FacilitySelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facility_selection)

        // 여기에 시설 선택과 관련된 로직을 추가하세요.
        findViewById<Button>(R.id.singing).setOnClickListener {
            startActivity(Intent(this, SingingActivity::class.java))
        }
        findViewById<Button>(R.id.pcroom).setOnClickListener {
            startActivity(Intent(this, PcActivity::class.java))
        }
        findViewById<Button>(R.id.billiards).setOnClickListener {
            startActivity(Intent(this, billiardsActivity::class.java))
        }
        findViewById<Button>(R.id.bowling).setOnClickListener {
            startActivity(Intent(this, bowlingActivity::class.java))
        }

    }
}