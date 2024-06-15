package id.my.miruza.locavest.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SendCartData(
    val id: Int,
    val quantity: Int
) : Parcelable
