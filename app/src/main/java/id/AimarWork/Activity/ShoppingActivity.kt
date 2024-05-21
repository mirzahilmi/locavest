package id.AimarWork.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.AimarWork.EnumPackageAimar.Category_Item_enum
import id.AimarWork.FileDataTemporary.fruits
import id.AimarWork.FileDataTemporary.meats
import id.AimarWork.FileDataTemporary.vegetables
import id.AimarWork.ModelDataClass.Category_Items
import id.AimarWork.RecyclerviewAimar.Adapter.Category_Items_Adapter
import id.my.miruza.locavest.R

class ShoppingActivity : AppCompatActivity() {

    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var categoryList: ArrayList<Category_Items>
    private lateinit var categoryItemAdapter : Category_Items_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        categoryList = ArrayList()
        categoryList.add(Category_Items(
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
        )
        )

        recyclerViewCategory = findViewById(R.id.recyclerViewCategory)
        recyclerViewCategory.setHasFixedSize(true)
        recyclerViewCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewCategory.setHasFixedSize(true)



        categoryItemAdapter = Category_Items_Adapter(this, categoryList)
        recyclerViewCategory.adapter = categoryItemAdapter
    }
}