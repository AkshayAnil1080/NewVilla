package com.example.newsvilla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this)
        val items = fetchData()             //3.
        val adapter: NewsVillaAdapter = NewsVillaAdapter(items)  //1. creating instance of adapter , data req is  AL of str, how to fetch it, lets mimick
        recyclerView.adapter = adapter  // 4.link recycler view with the adapter we made now in line 16.
    }
    private fun fetchData() : ArrayList<String> {       //2
        val list =ArrayList<String>()
        for(i in 0 until 100){
            list.add("Item $i")
        }
        return list
    }
}