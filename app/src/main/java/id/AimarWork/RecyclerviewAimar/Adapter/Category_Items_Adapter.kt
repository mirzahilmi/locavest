package id.AimarWork.RecyclerviewAimar.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import id.AimarWork.Fragment.ListOfItemFragment
import id.AimarWork.ModelDataClass.Category_Items
import id.my.miruza.locavest.R


class Category_Items_Adapter(
    private val context : Context,
    private val categoryList:ArrayList<Category_Items>)
    : RecyclerView.Adapter<Category_Items_Adapter.CategoryViewHolder>(){

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageHolder: ImageView = itemView.findViewById(R.id.category_container_data_image)
        val headerHolder: TextView = itemView.findViewById(R.id.category_container_data_header)
        val totalItemCount: TextView = itemView.findViewById(R.id.category_container_data_count)
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val category = categoryList[position]
                val fragment = ListOfItemFragment.newInstance(category.category)

                // Get the support fragment manager from the itemView's context
                val fragmentManager = (itemView.context as AppCompatActivity).supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentlayout_list_of_item, fragment)
                transaction.addToBackStack(null) // Add to back stack if needed
                transaction.commit() // Commit the transaction
                Log.d("Fragment Attempt", "Attempt to Load Fragment")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.imageHolder.setImageResource(category.image)
        holder.headerHolder.text = category.name
        holder.totalItemCount.text = "List of Item: (${category.totalItem})"
    }
}