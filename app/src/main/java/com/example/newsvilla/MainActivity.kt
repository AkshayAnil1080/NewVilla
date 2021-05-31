package com.example.newsvilla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this)
        val items = fetchData()             //3.
        val adapter: NewsVillaAdapter = NewsVillaAdapter(items,this)  //1. creating instance of adapter , data req is  AL of str, how to fetch it, lets mimick
        recyclerView.adapter = adapter  // 4.link recycler view with the adapter we made now in line 16.
    }
    private fun fetchData() : ArrayList<String> {       //2
        val list =ArrayList<String>()
        for(i in 0 until 100){
            list.add("Item $i")
        }
        return list
    }

    override fun onItemClicked(item: String) {
       Toast.makeText(this,"clicked item is $item", Toast.LENGTH_LONG).show()
    }
}

// step 8b. adapter has passed the listener to the activity, lets make sure activity recieves it.
// telling line 17 now this curr activity is your listener , it says, this main activity do not implements NewsItemClicked ,
// right click on this, so lets implement it , u can see line 8 has noe NewsItmeCLicked and we can implement the function of this interface too.