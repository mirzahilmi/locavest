package id.AimarWork.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import id.AimarWork.ModelDataClass.Format_Quantity
import id.AimarWork.ModelDataClass.Item_from_Category
import id.my.miruza.locavest.R

class DetailedItem(data: Item_from_Category) : Fragment() {
    lateinit var data : Item_from_Category
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

        val calculatedPrice  = CalculatePrice(data.price)
        val formatPrice = CalculateFormatPrice(data.price)
        val formatQuantity = GetFormat(data.quantity_type)


        data.apply {
            detailedItemImage.setImageResource(imageItem)
            detailedItemName.text = Name
        }
        detailedItemPrice.text ="${calculatedPrice}${formatPrice}"
        detailedItemFormatPrice.text = "Rupiah / ${formatQuantity}"
        detailedItemPricePerPiece.text = GetFormatPricePerPiece()

        val backButton = view.findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
        return view
    }

    companion object {
        fun newInstance(data : Item_from_Category) : DetailedItem{
            return DetailedItem(data)
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
    fun GetFormat(value : Format_Quantity) : String{
        if(value == Format_Quantity.Kilo) return "kg"
        if(value == Format_Quantity.Liter) return "l"
        if(value == Format_Quantity.Piece) return "biji"
        return ""
    }
    fun GetFormatPricePerPiece() : String{
        val pricePerPiece = data.weight_per_piece
        val formatQuantity = when(data.quantity_type){
            Format_Quantity.Kilo -> "g"
            Format_Quantity.Liter -> "ml"
            else -> "nigga"
        }
        return "${pricePerPiece}${formatQuantity}"
    }
}