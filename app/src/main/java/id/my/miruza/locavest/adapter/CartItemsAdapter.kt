package id.my.miruza.locavest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.my.miruza.locavest.R
import id.my.miruza.locavest.RetrofitInstance
import id.my.miruza.locavest.data.CartItem
import retrofit2.Callback
import id.my.miruza.locavest.databinding.LayoutCartItemBinding
import retrofit2.Call
import retrofit2.Response

class CartItemsAdapter(
    private val items: MutableList<CartItem>
) : RecyclerView.Adapter<CartItemsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: LayoutCartItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutCartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
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
                item.price,
                item.format,
            )
            cartItemCount.text = item.quantity.toString()
            cartItemDelete.setOnClickListener {
                RetrofitInstance.api.removeCartItems(item.id).enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                                items.removeAt(holder.adapterPosition)
                                notifyItemRemoved(holder.adapterPosition)
                                notifyItemRangeChanged(holder.adapterPosition, items.size)
                            }
                            Toast.makeText(
                                this@apply.root.context,
                                "Item deleted successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                this@apply.root.context,
                                "Failed to delete item",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Toast.makeText(
                            this@apply.root.context,
                            "Error: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }
        }
    }

    override fun getItemCount(): Int = items.size
}
