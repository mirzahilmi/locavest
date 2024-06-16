package id.my.miruza.locavest.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import id.my.miruza.locavest.data.ProductItem
import id.my.miruza.locavest.R
import id.my.miruza.locavest.RetrofitInstance
import id.my.miruza.locavest.data.CartItem
import id.my.miruza.locavest.data.SendCartData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailFragment(data: ProductItem) : Fragment() {
    lateinit var data : ProductItem
    init {
        this.data = data
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detailed_item, container, false)

        val detailedItemImage = view.findViewById<ImageView>(R.id.detailed_item_image_preview)
        val detailedItemName = view.findViewById<TextView>(R.id.detailed_item_name)
        val detailedItemPrice = view.findViewById<TextView>(R.id.detailed_item_price)
        val detailedItemFormatPrice = view.findViewById<TextView>(R.id.detailed_item_format_price)
        val detailedItemPricePerPiece = view.findViewById<TextView>(R.id.detailed_item_weight_per_format)
        val addToCartButton = view.findViewById<LinearLayout>(R.id.detailed_item_shop_cart_button)

        val calculatedPrice  = CalculatePrice(data.price)
        val formatPrice = CalculateFormatPrice(data.price)
        val formatQuantity = GetFormat(data.format)


        data.apply {
            //detailedItemImage.setImageResource(image)
            detailedItemName.text = name
        }
        detailedItemPrice.text ="${calculatedPrice}${formatPrice}"
        detailedItemFormatPrice.text = "Rupiah / ${formatQuantity}"
        detailedItemPricePerPiece.text = GetFormatPricePerPiece()

        val backButton = view.findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        addToCartButton.setOnClickListener{
            val cartItem = SendCartData(data.id, 1)
            //addToCart(cartItem)
        }
        return view
    }
    /*private fun addToCart(cartItem: SendCartData) {
        RetrofitInstance.api.addCartItems(cartItem).enqueue(object : Callback<SendCartData> {
            override fun onResponse(call: Call<SendCartData>, response: Response<SendCartData>) {
                if (response.isSuccessful) {
                    Log.d("AddToCart", "Item added to cart: ${response.body()}")
                } else {
                    Log.d("AddToCart", "Failed to add item to cart: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<SendCartData>, t: Throwable) {
                Log.e("AddToCart", "Error: ${t.message}", t)
            }
        })
    }*/
    companion object {
        fun newInstance(data : ProductItem) : ProductDetailFragment {
            return ProductDetailFragment(data)
        }
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
    fun GetFormatPricePerPiece() : String{
        val pricePerPiece = data.weightPerPiece
        val formatQuantity = when(data.format){
            "Kilo" -> "g"
            "Liter" -> "ml"
            else -> "nigga"
        }
        return "${pricePerPiece}${formatQuantity}"
    }
}