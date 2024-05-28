package id.my.miruza.locavest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartItem(
    var image: String,
    var name: String,
    var pricePer: Float,
    var priceUnit: String,
    var amount: Int,
): Parcelable
