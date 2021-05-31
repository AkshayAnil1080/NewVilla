1. Recycler view - Scrollable feature -  horz, vert, staggered(eg pinterest app) , grid(galary) 
List View- contains item vertically

Layout Manager - decides the alignment of  items  - > Recycler View < - Adapter(bind the items and put in the recycler)


take the xm of View and the data , bind them and feed into Recycler through view holder.
And u feed the View holder into the Recycler



1.File -> New Prject -> Empty Activity -> Name the app(i am naming NewsVilla)
 Couple of folders will be created along with activity_mail.xml and MainActivity.kt

2. 1.Open activity_main.xml Design - add a recycler view 
   Add dependecies in gradle.build
 	implementation("androidx.recyclerview:recyclerview:1.2.0")

   2.Set height width as 0dp and constarint left, right, top, bottom to parent
	<androidx.recyclerview.widget.RecyclerView
	android:id="@+id/recyclerView"
	android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"/>

3. Creating Layout Manager in activity.ky file.
	recyclerView.layoutManager = LinearLayoutManager(this)    -- using linear layout
	
	Note: to access the id from xml to kt file we need to add "id 'kotlin-android-extensions'" into build.gradle

4. Creating a adapter.
	create kotlin class NewsVillaAdapter
	 have to make it adapter of Recycler view ie. to extend(:) the class

class NewsVillaAdapter: RecyclerView.Adapter<>{  				 -- 1.Adapter hold the ViewHolder - the items to be passed in Recycler View.
    

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){	// 2. creating a view Holder

5. items that is repeated in the list - what will that be ?
 	will create in layout  -> new -> layout resource file -> say itemNews - we will make the xml of items

 Constarint layout of itrem view
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="16dp"
    xmlns:app="http://schemas.android.com/apk/res-auto">


    <TextView
        android:id="@+id/title"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        android:textSize="18sp"
        android:textColor="#212121"/>
</androidx.constraintlayout.widget.ConstraintLayout>

6. Need to call the item Textview having id title in NewsListAdapter.kt

class NewsVillaAdapter(val items: ArrayList<String>): RecyclerView.Adapter<NewsViewHolder>() {   // implement three function (Alt+enter) of adapter and it needs data lets give it a Arraylist for noe
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {		// returns actual NewsViewHolder(made below) and this NVH itself want an item view -where the item view will come from? from the xml- to convert xml to View format need LAYOUT INFALTOR
         val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false) // coerting xml to View format
         return NewsViewHolder(view)								// returning the newsviewHolder with the view in it.
    }

    override fun getItemCount(): Int {  							// number of items will be there in a list
      return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {   			// bind the data that is supposed to go into the holder function(corresponding view) how? it has got Position(which tells where to bind the data)
        val curretItem = items[position] 							// since items here are arraylist, accessing the curr item via position
	holder.titleView.text =curretItem							// bind the currItem with holder
    }
    // have to make it adapter of Recycler view ie. to extend(:) the class

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView: TextView = itemView.findViewById(R.id.title)


Adapter is complete now.

7. lets link it with Recycler View.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this) 
        val items = fetchData()					 // 3. call fetchdata and pass to adapter		
        val adapter: NewsVillaAdapter = NewsVillaAdapter(items)  // 1. creating instance of adapter , data req is  AL of str, how to fetch it, lets mimick , make a func fetchdata
recyclerView.adapter = adapter  // 4.link recycler view with the adapter we made now in line 16.
	
    }
    private fun fetchData() : ArrayList<String> { 2// to fetch the data , giving type as ArrayList since data is ArrayList of Strings
        val list =ArrayList<String>()		  // creating AL
        for(i in 0 until 100){			// run for loop to append Items in list 
            list.add("Item $i")
        }
        return list
    }
}

Done,running the app at this point will show a scrollable page in app with item 0 - item 99
