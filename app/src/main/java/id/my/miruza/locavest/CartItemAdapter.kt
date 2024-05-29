package id.my.miruza.locavest

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.my.miruza.locavest.databinding.LayoutCartItemBinding

class CartItemAdapter(
    private val items: List<CartItem>
) : RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutCartItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutCartItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.apply {
            val item = items[position]
            val glideOptions = RequestOptions()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder_broken)
            Log.w("Locavest", "Loading image URL: ${item.image}")
            Glide
                .with(holder.itemView.context)
                .load(item.image)
                .apply(glideOptions)
                .into(cartItemPicture)
            cartItemName.text = item.name
            cartItemPricing.text = holder.itemView.context.getString(
                R.string.cart_item_pricing_format,
                item.pricePer,
                item.priceUnit,
            )
            cartItemCount.text = item.amount.toString()
        }
    }

    override fun getItemCount(): Int = items.size
}

//@Parcelize
//data class CartItem(
//    var image: String,
//    var name: String,
//    var pricePer: Float,
//    var priceUnit: String,
//    var amount: Int,
//): Parcelable {
//    constructor(parcel: Parcel) : this(
//        parcel.readString()!!,
//        parcel.readString()!!,
//        parcel.readFloat(),
//        parcel.readString()!!,
//        parcel.readInt()
//    )
//
//    companion object : Parceler<CartItem> {
//
//        override fun CartItem.write(parcel: Parcel, flags: Int) {
//            parcel.writeString(image)
//            parcel.writeString(name)
//            parcel.writeFloat(pricePer)
//            parcel.writeString(priceUnit)
//            parcel.writeInt(amount)
//        }
//
//        override fun create(parcel: Parcel): CartItem {
//            return CartItem(parcel)
//        }
//    }
//}
