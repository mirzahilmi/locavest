package id.my.miruza.locavest

import id.my.miruza.locavest.data.CartItem
import id.my.miruza.locavest.data.SendCartData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SuperAPI {
    @GET("/api/carts")
    fun getCartItems(): Call<List<CartItem>>
    @POST("/carts")
    fun addCartItems(@Body data : SendCartData): Call<SendCartData>
}