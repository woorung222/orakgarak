package com.capstone.orakgarak.ui.facility

import MyListAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.orakgarak.R

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
            MyListAdapter.ItemData("Title 1", "Subtitle 1", R.drawable.icon),
            MyListAdapter.ItemData("Title 2", "Subtitle 2", R.drawable.icon),
            MyListAdapter.ItemData("Title 3", "Subtitle 3", R.drawable.icon)
        )

        // 어댑터 생성 및 설정
        adapter = MyListAdapter(itemList)
        recyclerView.adapter = adapter
    }
}

