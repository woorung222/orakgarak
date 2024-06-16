package com.capstone.orakgarak.ui.facility

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.orakgarak.R
import com.capstone.orakgarak.model.Store

class MyListAdapter(
    private val itemList: List<ItemData>,
    private val onItemClick: (ItemData) -> Unit
) : RecyclerView.Adapter<MyListAdapter.MyViewHolder>() {

    data class ItemData(
        val title: String,
        val subtitle: String,
        val iconRes: Int,
        val store: Store
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.titleTextView.text = currentItem.title
        holder.subtitleTextView.text = currentItem.subtitle
        holder.iconImageView.setImageResource(currentItem.iconRes)
        holder.itemView.setOnClickListener { onItemClick(currentItem) }
    }

    override fun getItemCount() = itemList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textViewTitle)
        val subtitleTextView: TextView = itemView.findViewById(R.id.textViewSubtitle)
        val iconImageView: ImageView = itemView.findViewById(R.id.imageView)
    }
}
