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

Done,running the app at this point will show a scrollable page in app with item 0 - item 99 -> each item is taking full screen now - therefore edit height in xml as wrapcontent


8. Lets handle the number if clicks in each item.
Go to adapter, and handle it before returning the ViewNewsholder

 view.setOnClickListener{			//mentio the work to be performed after the click 

        }
And activity should be responsible to handle all this work and not of adapter
How thw activity will come to know that item has been clicked ?  Need a callback - where adapter tells the activity about this particular item has been clicked 

 8a. Implamentation of Call back  - simple way is to create with interfaces like setOnClick is itself is an interface.

class NewsVillaAdapter(private val items: ArrayList<String> , private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {   // 2. adapter will provide the instance of this interface 
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        
        val viewHolder = NewsViewHolder(view)		// 4. before returning viewHolder lets make one instance 
        view.setOnClickListener{
           listener.onItemClicked(items[viewHolder.absoluteAdapterPosition])		// 3.passing the work ie instance of interface NewItemsClicked acessing its function
											// adapter  ki pos jo v hogi is viewholder ko mil jaega and items ka pos nikala aur pass kar diya

        }
        return viewHolder
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

interface NewsItemClicked{
    fun onItemClicked(item : String)   // 1.create a function and specifying its type
                                        

8b. Now, adapter has passed the listener to the activity, lets make sure activity recieves it.
go to mainActivity.xml
change the line of  val adapter: NewsVillaAdapter = NewsVillaAdapter(items) to val adapter: NewsVillaAdapter = NewsVillaAdapter(items,this)
// telling  this curr activity is your listener , it says, this main activity do not implements NewsItemClicked ,
// right click on this, so lets implement it , u can see line class has now NewsItmeCLicked and we can implement the function of this NewsItemClicked interface too.

override fun onItemClicked(item: String) {
       Toast.makeText(this,"clicked item is $item", Toast.LENGTH_LONG).show()
    }

Done, now running the app at this point will show a scrollable page in app with item 0 - item 99 -> and now if u click in ony item , it will display a message and will take you to that item.
Summary - how adapter works in a recycler view


9. Last step is to add news into the items via API call.
