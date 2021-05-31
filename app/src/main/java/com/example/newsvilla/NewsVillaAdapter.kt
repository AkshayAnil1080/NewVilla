package com.example.newsvilla

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class NewsVillaAdapter(private val items: ArrayList<String>): RecyclerView.Adapter<NewsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {  // number of items will be there in a list
      return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {   // bind the data that is supposed to go into the holder function
        val curretItem = items[position]
        holder.titleView.text =curretItem
    }
    // have to make it adapter of Recycler view ie. to extend(:) the class

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.title)
}