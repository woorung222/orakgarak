package com.capstone.orakgarak.ui

import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.orakgarak.R
import com.capstone.orakgarak.NaverApiResponse
import com.capstone.orakgarak.NaverApiService
import com.capstone.orakgarak.model.Store
import com.google.gson.GsonBuilder
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var naverMap: NaverMap
    private lateinit var searchEditText: EditText
    private lateinit var naverApiService: NaverApiService

    private val naverClientId = "9l7vyq712o"
    private val naverClientSecret = "LIlPUvAwgOxeue8xAaA8R3kEirpkIByeFHPIl4vl"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // LinearLayout 생성 및 설정
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        // EditText 생성 및 설정
        searchEditText = EditText(this).apply {
            hint = "검색어 입력"
        }

        // MapView 생성 및 설정
        mapView = MapView(this).apply {
            onCreate(savedInstanceState)
        }

        // LinearLayout에 EditText와 MapView 추가
        layout.addView(searchEditText, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layout.addView(mapView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)

        // Activity의 콘텐츠 뷰로 설정
        setContentView(layout)

        // MapView 초기화
        mapView.getMapAsync(this)

        // Retrofit 초기화
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()

        // NaverApiService 초기화
        naverApiService = retrofit.create(NaverApiService::class.java)

        searchEditText.setOnEditorActionListener { _, _, _ ->
            val query = searchEditText.text.toString()
            searchLocation(query)
            true
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap

        // 전달된 Store 객체 가져오기
        val store: Store? = intent.getParcelableExtra("STORE")

        // 지도에 마커 추가
        store?.let {
            val location = LatLng(it.latitude, it.longitude)
            val marker = Marker()
            marker.position = location
            marker.map = naverMap

            val cameraUpdate = CameraUpdate.scrollTo(location)
            naverMap.moveCamera(cameraUpdate)
        } ?: run {
            Toast.makeText(this, "Store data not available", Toast.LENGTH_SHORT).show()
        }
    }

    private fun searchLocation(query: String) {
        val encodedQuery = URLEncoder.encode(query, "UTF-8")
        val call = naverApiService.searchPlaces(naverClientId, naverClientSecret, encodedQuery)

        call.enqueue(object : Callback<NaverApiResponse> {
            override fun onResponse(
                call: Call<NaverApiResponse>,
                response: Response<NaverApiResponse>
            ) {
                if (response.isSuccessful) {
                    val addresses = response.body()?.addresses
                    if (!addresses.isNullOrEmpty()) {
                        val address = addresses[0]
                        val location = LatLng(address.y, address.x)

                        val marker = Marker()
                        marker.position = location
                        marker.map = naverMap

                        val cameraUpdate = CameraUpdate.scrollTo(location)
                        naverMap.moveCamera(cameraUpdate)
                    } else {
                        Toast.makeText(
                            this@MapActivity,
                            "검색 결과가 없습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@MapActivity,
                        "검색 실패: ${response.code()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<NaverApiResponse>, t: Throwable) {
                Toast.makeText(
                    this@MapActivity,
                    "네트워크 오류: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
