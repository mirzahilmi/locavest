package id.my.miruza.locavest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import id.my.miruza.locavest.R
import id.my.miruza.locavest.adapter.CategoriesAdapter
import id.my.miruza.locavest.data.CategoryItem


class CategoriesFragment : Fragment() {
    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var categoryList: ArrayList<CategoryItem>
    private lateinit var categoryItemAdapter : CategoriesAdapter
    private lateinit var database : FirebaseDatabase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance()
        val categoryRef = database.getReference("Categories")

        categoryList = ArrayList()
        categoryRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                categoryList.clear()  // Clear existing data
                for (childSnapshot in snapshot.children) {
                    val categoryItem = childSnapshot.getValue<CategoryItem>()!!
                    val imageUrl: String? = childSnapshot.child("image").getValue<String>()  // Access "image" property

                    categoryItem.image = imageUrl
                    categoryList.add(categoryItem)
                }
                categoryItemAdapter.notifyDataSetChanged()  // Update adapter data
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors
            }
        })

        recyclerViewCategory = view.findViewById(R.id.recyclerViewCategory)
        recyclerViewCategory.setHasFixedSize(true)
        recyclerViewCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewCategory.setHasFixedSize(true)

        categoryItemAdapter = CategoriesAdapter(requireContext(), categoryList)
        recyclerViewCategory.adapter = categoryItemAdapter
    }
}