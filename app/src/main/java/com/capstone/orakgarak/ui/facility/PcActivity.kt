package com.capstone.orakgarak.ui.facility

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.orakgarak.R
import com.capstone.orakgarak.model.Store

class PcActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pcroom)

        // RecyclerView 설정
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 데이터 리스트 생성
        val itemList = listOf(
            MyListAdapter.ItemData(
                "긱스타PC 숭실1호점", "서울 동작구 사당로 28", R.drawable.icon,
                Store(
                    name = "긱스타PC 숭실1호점",
                    address = "서울 동작구 사당로 28",
                    phone = "NULL",
                    description = "Store 1 Description",
                    hours = "00:00~24:00",
                    amenities = "무료 WIFI, 흡연장, 프린터",
                    latitude = 37.4949353,
                    longitude = 126.9574609
                )
            ),
            MyListAdapter.ItemData(
                "긱스타PC 숭실2호점", "서울 동작구 상도로67길 32", R.drawable.icon,
                Store(
                    name = "긱스타PC 숭실2호점",
                    address = "서울 동작구 상도로67길 32",
                    phone = "NULL",
                    description = "Store 2 Description",
                    hours = "00:00~24:00",
                    amenities = "무료 WIFI, 흡연장, 프린터",
                    latitude = 37.4949760,
                    longitude = 126.9566665
                )
            ),
            MyListAdapter.ItemData(
                "용PC", "서울 동작구 상도로61길 40", R.drawable.icon,
                Store(
                    name = "용PC",
                    address = "서울 동작구 상도로61길 40",
                    phone = "NULL",
                    description = "Store 3 Description",
                    hours = "00:00~24:00",
                    amenities = "무료 WIFI, 흡연장, 프린터",
                    latitude = 37.4946620,
                    longitude = 126.9570925
                )
            ),
            MyListAdapter.ItemData(
                "하늘다리 PC방", "서울 동작구 사당로 4", R.drawable.icon,
                Store(
                    name = "하늘다리 PC방",
                    address = "서울 동작구 사당로 4",
                    phone = "NULL",
                    description = "Store 3 Description",
                    hours = "00:00~24:00",
                    amenities = "무료 WIFI, 흡연장, 프린터",
                    latitude = 37.4954152,
                    longitude = 126.9548687
                )
            ),
            MyListAdapter.ItemData(
                "더블유PC방", "서울 동작구 상도로63길 15", R.drawable.icon,
                Store(
                    name = "더블유PC방",
                    address = "서울 동작구 상도로63길 15",
                    phone = "0507-1322-1524",
                    description = "Store 3 Description",
                    hours = "00:00~24:00",
                    amenities = "무료 WIFI, 흡연장, 프린터, 숭실대 중문 건넌후에 직진후 사거리 지나서 오른쪽 이레김밥 바로 옆 지하1층",
                    latitude = 37.4947154,
                    longitude = 126.9563371

                )
            )
        )

        // 어댑터 생성 및 설정
        adapter = MyListAdapter(itemList) { item ->
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("STORE", item.store)
            }
            startActivity(intent)
        }
        recyclerView.adapter = adapter
    }
}
