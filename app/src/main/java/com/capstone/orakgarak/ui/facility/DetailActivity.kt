package com.capstone.orakgarak.ui.facility

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.capstone.orakgarak.R
import com.capstone.orakgarak.model.Store
import com.capstone.orakgarak.ui.MapActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        @Suppress("DEPRECATION")
        val store = intent.getParcelableExtra<Store>("STORE")

        val storeImage = findViewById<ImageView>(R.id.imageView)
        val storeName = findViewById<TextView>(R.id.storeName)
        val storeAddress = findViewById<TextView>(R.id.storeAddress)
        val storePhone = findViewById<TextView>(R.id.storePhone)
        val storeDescription = findViewById<TextView>(R.id.storeDescription)
        val storeHours = findViewById<TextView>(R.id.storeHours)
        val storeAmenities = findViewById<TextView>(R.id.storeAmenities)
        val iconMap = findViewById<ImageView>(R.id.iconMap)
        val checkAvailabilityButton = findViewById<Button>(R.id.checkAvailabilityButton)

        // Store 데이터 설정
        store?.let {
            storeName.text = it.name
            storeAddress.text = "주소: ${it.address}"
            storePhone.text = "전화번호: ${it.phone}"
            storeDescription.text = "영업 시설 및 시설 소개"
            storeHours.text = "영업시간: ${it.hours}"
            storeAmenities.text = it.amenities
        }

        // 지도 아이콘 클릭 리스너 설정
        iconMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java).apply {
                putExtra("STORE", store)
            }
            startActivity(intent)
        }

        // 빈자리 확인하기 버튼 클릭 리스너 설정
        checkAvailabilityButton.setOnClickListener {
            // 빈자리 확인하기 기능 추가
            // 예: 다른 액티비티로 이동하거나 다이얼로그 표시
        }
    }
}
