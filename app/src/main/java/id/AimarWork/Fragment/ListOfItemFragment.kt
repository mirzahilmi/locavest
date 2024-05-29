package id.AimarWork.Fragment

import android.os.Bundle
import android.util.Log
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
import id.AimarWork.EnumPackageAimar.Category_Item_enum
import id.AimarWork.ModelDataClass.Item_from_Category
import id.AimarWork.RecyclerviewAimar.Adapter.Item_From_Category_Adapter
import id.my.miruza.locavest.R

class ListOfItemFragment : Fragment() {
    private lateinit var categoryChoice : String
    private lateinit var itemList : ArrayList<Item_from_Category>
    private lateinit var ItemFromCategoryRecyclerView : RecyclerView
    private lateinit var ItemFromCategoryAdapater : Item_From_Category_Adapter
    private lateinit var database : FirebaseDatabase
    private lateinit var CategoryHeader : TextView
    companion object{
        fun newInstance(category_choice : String ): ListOfItemFragment{
            val fragment = ListOfItemFragment()
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
        val categoryEnum = Category_Item_enum.valueOf(categoryChoice)

        ItemFromCategoryRecyclerView = view.findViewById(R.id.recyclerViewListOfItem)
        ItemFromCategoryRecyclerView.setHasFixedSize(true)
        ItemFromCategoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())



        CategoryHeader = view.findViewById(R.id.category_type)
        CategoryHeader.text = categoryEnum.toString()
        itemList = ArrayList()
        var databaseReference = database.getReference(categoryChoice)
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for(childSnapshot in snapshot.children){
                    val item = childSnapshot.getValue<Item_from_Category>()!!
                    itemList.add(item)
                }
                ItemFromCategoryAdapater.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
        //Log.d("ListOfItemActivity", "$itemList")
        val backButton = view.findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        // Inflate the layout for this fragment
        //Log.d("Fragment Category", "$categoryEnum")
        ItemFromCategoryAdapater = Item_From_Category_Adapter(requireContext(), itemList)
        ItemFromCategoryRecyclerView.adapter = ItemFromCategoryAdapater
        return view
    }
}