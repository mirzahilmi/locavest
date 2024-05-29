package id.my.miruza.locavest.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.my.miruza.locavest.fragment.ProductsFragment
import id.my.miruza.locavest.data.CategoryItem
import id.my.miruza.locavest.R


class CategoriesAdapter(
    private val context : Context,
    private val categoryList:ArrayList<CategoryItem>)
    : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>(){

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
                val fragment = ProductsFragment.newInstance(category.name)

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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_category_item, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        //holder.imageHolder.setImageResource(category.image)
        holder.headerHolder.text = category.name
        holder.totalItemCount.text = "List of Item: (${category.totalItem})"
        Glide.with(holder.itemView.context)
            .load(category.image)
            .into(holder.imageHolder)
        holder.headerHolder.text = category.name
        holder.totalItemCount.text = "List of Item: (${category.totalItem})"
    }
}