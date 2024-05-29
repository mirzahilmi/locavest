package id.my.miruza.locavest.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import id.my.miruza.locavest.data.ProductItem
import id.my.miruza.locavest.adapter.ProductsAdapter
import id.my.miruza.locavest.R

class ProductsFragment : Fragment() {
    private lateinit var categoryChoice : String
    private lateinit var itemList : ArrayList<ProductItem>
    private lateinit var ItemFromCategoryRecyclerView : RecyclerView
    private lateinit var ItemFromCategoryAdapater : ProductsAdapter
    private lateinit var database : FirebaseDatabase
    private lateinit var CategoryHeader : TextView
    companion object{
        fun newInstance(category_choice : String ): ProductsFragment {
            val fragment = ProductsFragment()
            val args = Bundle()
            args.putString("category", category_choice)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_of_item, container, false)
        database = FirebaseDatabase.getInstance()
        categoryChoice = arguments?.getString("category") ?:""

        ItemFromCategoryRecyclerView = view.findViewById(R.id.recyclerViewListOfItem)
        ItemFromCategoryRecyclerView.setHasFixedSize(true)
        ItemFromCategoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())



        CategoryHeader = view.findViewById(R.id.category_type)
        CategoryHeader.text = categoryChoice
        itemList = ArrayList()
        var databaseReference = database.getReference(categoryChoice)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(childSnapshot in snapshot.children){
                    val item = childSnapshot.getValue<ProductItem>()!!
                    itemList.add(item)
                }
                ItemFromCategoryAdapater.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        val backButton = view.findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        ItemFromCategoryAdapater = ProductsAdapter(requireContext(), itemList)
        ItemFromCategoryRecyclerView.adapter = ItemFromCategoryAdapater
        return view
    }
}