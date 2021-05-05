package com.example.wally

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListAdapter(val context: Context, private val data: MutableList<ListItem>): RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.list_item_image)
        val user: TextView = view.findViewById(R.id.list_item_user)
        val likes: TextView = view.findViewById(R.id.list_item_likes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(data[position].imageResource)
            .into(holder.image)

        holder.likes.text = data[position].Likes.toString()
        holder.user.text = data[position].user
    }

    override fun getItemCount(): Int {
        return data.size
    }
}