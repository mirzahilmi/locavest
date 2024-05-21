package id.AimarWork.RecyclerviewAimar.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import id.AimarWork.Fragment.DetailedItem
import id.AimarWork.ModelDataClass.Format_Quantity
import id.AimarWork.ModelDataClass.Item_from_Category
import id.my.miruza.locavest.R


class Item_From_Category_Adapter(
    private val context: Context,
    private val itemList: ArrayList<Item_from_Category>
) : RecyclerView.Adapter<Item_From_Category_Adapter.ItemFromCategoryViewHolder>() {

    inner class ItemFromCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageHolder: ImageView = itemView.findViewById(R.id.item_image)
        val textHolder: TextView = itemView.findViewById(R.id.item_name)
        val priceHolder : TextView = itemView.findViewById(R.id.price)
        val format_quantity : TextView = itemView.findViewById(R.id.format_price)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = itemList[position]


                val fragment = DetailedItem.newInstance(item)

                val fragmentManager =
                    (itemView.context as AppCompatActivity).supportFragmentManager
                val transaction = fragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentlayout_detailed_item, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
                Log.d("SubFragment Attempt", "SubAttempt to Load Fragment")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemFromCategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_from_category, parent, false)
        return ItemFromCategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemFromCategoryViewHolder, position: Int) {
        val item = itemList[position]
        holder.imageHolder.setImageResource(item.imageItem)
        holder.textHolder.text = item.Name

        val calculatedPrice = CalculatePrice(item.price)
        val formattedPrice = CalculateFormatPrice(item.price)
        val formatItem = GetFormat(item.quantity_type)
        holder.priceHolder.text = "${calculatedPrice}${formattedPrice}"
        holder.format_quantity.text = "Rupiah /${formatItem}"
    }
    fun CalculatePrice(value : Float) : Float{
        if(value > 1000000) return value/1000000
        else if(value > 1000) return value/1000
        else return value
    }
    fun CalculateFormatPrice(value : Float) : String{
        if(value > 1000000) return "jt"
        else if(value > 1000) return "k"
        else return ""
    }
    fun GetFormat(value : Format_Quantity) : String{
        if(value == Format_Quantity.Kilo) return "kg"
        if(value == Format_Quantity.Liter) return "l"
        if(value == Format_Quantity.Piece) return "biji"
        return ""
    }
}
