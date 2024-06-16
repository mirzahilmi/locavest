package id.my.miruza.locavest.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SendCartData(
    val productID: Int,
    val quantity: Int
) : Parcelable
