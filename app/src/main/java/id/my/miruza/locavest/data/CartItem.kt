package id.my.miruza.locavest.data

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartItem(
    val id: Int,
    val quantity: Int,
    val name: String,
    val image: String,
    val format: String,
    val price: Float,
): Parcelable
