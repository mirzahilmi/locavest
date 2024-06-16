package id.my.miruza.locavest

import id.my.miruza.locavest.data.CartItem
import id.my.miruza.locavest.data.SendCartData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SuperAPI {
    @GET("/api/carts")
    fun getCartItems(): Call<List<CartItem>>
    @POST("/api/carts")
    fun addCartItems(@Body data : SendCartData): Call<ResponseBody>
    @POST("/api/carts/checkout")
    fun checkout(): Call<Void>
    @DELETE("/api/carts/{id}")
    fun removeCartItems(@Path("id") id: Int): Call<Void>
}