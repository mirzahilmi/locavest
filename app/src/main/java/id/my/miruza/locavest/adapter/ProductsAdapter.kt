package id.my.miruza.locavest.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.my.miruza.locavest.fragment.ProductDetailFragment
import id.my.miruza.locavest.data.ProductItem
import id.my.miruza.locavest.R
import id.my.miruza.locavest.RetrofitInstance
import id.my.miruza.locavest.data.CartItem
import id.my.miruza.locavest.data.SendCartData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductsAdapter(
    private val context: Context,
    private val itemList: ArrayList<ProductItem>
) : RecyclerView.Adapter<ProductsAdapter.ItemFromCategoryViewHolder>() {

    inner class ItemFromCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val imageHolder: ImageView = itemView.findViewById(R.id.item_image)
        val textHolder: TextView = itemView.findViewById(R.id.item_name)
        val priceHolder : TextView = itemView.findViewById(R.id.price)
        val formatQuantity : TextView = itemView.findViewById(R.id.format_price)
        val addToCartButton : ImageView = itemView.findViewById(R.id.add_to_cart_button)


        init {
            itemView.setOnClickListener(this)
            addToCartButton.setOnClickListener(View.OnClickListener {
                val productItem = itemList[adapterPosition]
                val cartItem = SendCartData(productItem.id, 1)
                addToCart(cartItem)
            })
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = itemList[position]


                val fragment = ProductDetailFragment.newInstance(item)

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
            .inflate(R.layout.layout_product_item, parent, false)
        return ItemFromCategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ItemFromCategoryViewHolder, position: Int) {
        val item = itemList[position]
        Glide.with(holder.itemView.context)
            .load(item.image)
            .into(holder.imageHolder)
        holder.textHolder.text = item.name

        val calculatedPrice = CalculatePrice(item.price)
        val formattedPrice = CalculateFormatPrice(item.price)
        val formatItem = GetFormat(item.format)
        holder.priceHolder.text = "${calculatedPrice}${formattedPrice}"
        holder.formatQuantity.text = "Rupiah /${formatItem}"
    }
    private fun addToCart(cartItem: SendCartData) {
        RetrofitInstance.api.addCartItems(cartItem).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Item successfully added to cart", Toast.LENGTH_SHORT).show()
                    Log.d("AddToCart", "Item added to cart: ${response.body()}")
                } else {
                    Toast.makeText(context, "Item failed to be added to cart", Toast.LENGTH_SHORT).show()
                    Log.d("AddToCart", "Failed to add item to cart: ${response.errorBody()?.string()} ${response.headers()}")
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(context, "Item failed to be added to cart", Toast.LENGTH_SHORT).show()
                Log.e("AddToCart", "Error: ${t.cause}", t)
            }
        })
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
    fun GetFormat(value : String) : String{
        if(value == "Kilo") return "kg"
        if(value == "Liter") return "l"
        if(value == "Piece") return "biji"
        return ""
    }
}
