package id.my.miruza.locavest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.my.miruza.locavest.R
import id.my.miruza.locavest.data.CartItem
import id.my.miruza.locavest.databinding.LayoutCartItemBinding

class CartItemsAdapter(
    private val items: List<CartItem>
) : RecyclerView.Adapter<CartItemsAdapter.ViewHolder>() {

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
