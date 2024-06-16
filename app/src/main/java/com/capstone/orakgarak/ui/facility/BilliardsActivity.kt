package com.capstone.orakgarak.ui.facility

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.orakgarak.R
import com.capstone.orakgarak.model.Store

class BilliardsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billiards)

        // RecyclerView 설정
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 데이터 리스트 생성
        val itemList = listOf(
            MyListAdapter.ItemData(
                "오페라당구장", "서울 동작구 상도로58길 4", R.drawable.icon,
                Store(
                    name = "오페라당구장",
                    address = "서울 동작구 상도로58길 4",
                    phone = "02-822-7824",
                    description = "Store 1 Description",
                    hours = "9:00 ~ 18:00",
                    amenities = "무료 WIFI,흡연실",
                    latitude = 37.4959177,
                    longitude = 126.9530780
                )
            ),
            MyListAdapter.ItemData(
                "숭실당구장", "서울 동작구 상도로62길 10", R.drawable.icon,
                Store(
                    name = "숭실당구장",
                    address = "서울 동작구 상도로62길 10",
                    phone = "070-8100-1573",
                    description = "Store 2 Description",
                    hours = "매일 12:00 ~ 24:00",
                    amenities = "무료 WIFI",
                    latitude = 37.4949618,
                    longitude = 126.9541967
                )
            ),
            MyListAdapter.ItemData(
                "백마당구장", "서울 동작구 사당로 6-1", R.drawable.icon,
                Store(
                    name = "백마당구장",
                    address = "서울 동작구 사당로 6-1",
                    phone = "02-814-4891",
                    description = "Store 3 Description",
                    hours = "NULL",
                    amenities = "흡연장",
                    latitude = 37.4953514,
                    longitude = 126.9551885
                )
            ),
            MyListAdapter.ItemData(
                "동아리당구장", "서울 동작구 사당로 18-1", R.drawable.icon,
                Store(
                    name = "동아리당구장",
                    address = "서울 동작구 사당로 18-1",
                    phone = "070-4250-1230",
                    description = "Store 3 Description",
                    hours = "12:00 ~ 22:00",
                    amenities = "3층,취식 가능, 흡연장",
                    latitude = 37.4951904,
                    longitude = 126.9564582
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
