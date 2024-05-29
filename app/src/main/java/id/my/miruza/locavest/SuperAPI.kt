package id.my.miruza.locavest

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface SuperAPI {
    @GET("/api/carts")
    fun getCartItems(): Call<List<CartItem>>
}