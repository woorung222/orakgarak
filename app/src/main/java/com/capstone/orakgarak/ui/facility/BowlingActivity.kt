package com.capstone.orakgarak.ui.facility

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.orakgarak.R
import com.capstone.orakgarak.model.Store

class BowlingActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bowling)

        // RecyclerView 설정
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 데이터 리스트 생성
        val itemList = listOf(
            MyListAdapter.ItemData(
                "NULL", "NULL", R.drawable.icon,
                Store(
                    name = "NULL",
                    address = "NULL",
                    phone = "NULL",
                    description = "Store 1 Description",
                    hours = "NULL",
                    amenities = "NULL",
                    latitude = 37.5665,
                    longitude = 126.9780
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
