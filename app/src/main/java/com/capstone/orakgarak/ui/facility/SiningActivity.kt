package com.capstone.orakgarak.ui.facility

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.orakgarak.R
import com.capstone.orakgarak.model.Store

class SingingActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singing)

        // RecyclerView 설정
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 데이터 리스트 생성
        val itemList = listOf(
            MyListAdapter.ItemData(
                "비트코인동전노래연습장숭실대직영점", "서울 동작구 상도로61길 34", R.drawable.icon,
                Store(
                    name = "비트코인동전노래연습장숭실대직영점",
                    address = "서울 동작구 상도로61길 34",
                    phone = "NULL",
                    description = "Store 1 Description",
                    hours = "00:00~24:00",
                    amenities = "흡연실",
                    latitude = 37.4947873,
                    longitude = 126.9568110
                )
            ),
            MyListAdapter.ItemData(
                "리코스타코인노래연습장 숭실대점", "서울 동작구 상도로67길 31", R.drawable.icon,
                Store(
                    name = "Store 2",
                    address = "서울 동작구 상도로67길 31",
                    phone = "0507-1301-5717",
                    description = "Store 2 Description",
                    hours = "매일 7:00 - 03:00",
                    amenities = "지하 1층 ,무료 WIFI",
                    latitude = 37.4950150,
                    longitude = 126.9564293
                )
            ),
            MyListAdapter.ItemData(
                "짱노래방", "서울 동작구 사당로 16", R.drawable.icon,
                Store(
                    name = "짱노래방",
                    address = "서울 동작구 사당로 16",
                    phone = "NULL",
                    description = "Store 3 Description",
                    hours = "NULL",
                    amenities = "NULL",
                    latitude = 37.4952407,
                    longitude = 37.4952407
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
