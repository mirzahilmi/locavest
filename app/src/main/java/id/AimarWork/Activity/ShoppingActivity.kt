package id.AimarWork.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import id.AimarWork.ModelDataClass.Category_Items
import id.AimarWork.RecyclerviewAimar.Adapter.Category_Items_Adapter
import id.my.miruza.locavest.R

class ShoppingActivity : AppCompatActivity() {

    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var categoryList: ArrayList<Category_Items>
    private lateinit var categoryItemAdapter : Category_Items_Adapter
    private lateinit var database : FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        database = FirebaseDatabase.getInstance()
        val categoryRef = database.getReference("Categories")

        categoryList = ArrayList()
        categoryRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                categoryList.clear()  // Clear existing data
                for (childSnapshot in snapshot.children) {
                    val categoryItem = childSnapshot.getValue<Category_Items>()!!
                    val imageUrl : String?
                    imageUrl = childSnapshot.child("image").getValue<String>()  // Access "image" property
                    Log.d(imageUrl, "Image URL: ")
                    Toast.makeText(baseContext, imageUrl, Toast.LENGTH_LONG).show()
                    // Update categoryItem with both name and image URL
                    categoryItem.image = imageUrl
                    categoryList.add(categoryItem)
                }
                categoryItemAdapter.notifyDataSetChanged()  // Update adapter data
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
        /*categoryList.add(Category_Items(
            "Vegetables",
            R.drawable.veggie,
            Category_Item_enum.Vegetable,
            vegetables.count()
            ))
        categoryList.add(Category_Items(
            "Fruit",
            R.drawable.fruitie,
            Category_Item_enum.Fruit,
            fruits.count()
        ))
        categoryList.add(Category_Items(
            "Meat",
            R.drawable.meaty,
            Category_Item_enum.Meat,
            meats.count()
        ))*/

        recyclerViewCategory = findViewById(R.id.recyclerViewCategory)
        recyclerViewCategory.setHasFixedSize(true)
        recyclerViewCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewCategory.setHasFixedSize(true)



        categoryItemAdapter = Category_Items_Adapter(this, categoryList)
        recyclerViewCategory.adapter = categoryItemAdapter
    }
}