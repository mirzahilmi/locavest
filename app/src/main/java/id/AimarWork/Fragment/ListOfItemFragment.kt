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
import id.AimarWork.EnumPackageAimar.Category_Item_enum
import id.AimarWork.FileDataTemporary.fruits
import id.AimarWork.FileDataTemporary.meats
import id.AimarWork.FileDataTemporary.vegetables
import id.AimarWork.ModelDataClass.Item_from_Category
import id.AimarWork.RecyclerviewAimar.Adapter.Item_From_Category_Adapter
import id.my.miruza.locavest.R

class ListOfItemFragment : Fragment() {
    private lateinit var categoryEnumName : String
    private lateinit var itemList : ArrayList<Item_from_Category>
    private lateinit var ItemFromCategoryRecyclerView : RecyclerView
    private lateinit var ItemFromCategoryAdapater : Item_From_Category_Adapter

    private lateinit var CategoryHeader : TextView
    companion object{
        fun newInstance(category_enum : Category_Item_enum ): ListOfItemFragment{
            val fragment = ListOfItemFragment()
            val args = Bundle()
            args.putString("category", category_enum.name)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_of_item, container, false)

        categoryEnumName = arguments?.getString("category") ?:""
        val categoryEnum = Category_Item_enum.valueOf(categoryEnumName)

        ItemFromCategoryRecyclerView = view.findViewById(R.id.recyclerViewListOfItem)
        ItemFromCategoryRecyclerView.setHasFixedSize(true)
        ItemFromCategoryRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        CategoryHeader = view.findViewById(R.id.category_type)
        CategoryHeader.text = categoryEnum.toString()

        itemList = when (categoryEnum) {
            Category_Item_enum.Vegetable -> vegetables
            Category_Item_enum.Fruit -> fruits
            Category_Item_enum.Meat -> meats
        }
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