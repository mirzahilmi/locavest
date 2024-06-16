package id.my.miruza.locavest.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import id.my.miruza.locavest.data.CartItem
import id.my.miruza.locavest.R
import id.my.miruza.locavest.RetrofitInstance
import id.my.miruza.locavest.databinding.ActivityCheckoutBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cartItems: List<CartItem> = intent.extras?.getParcelableArrayList("cartItems")!!
        var totalPrice = 0F
        for (item: CartItem in cartItems) {
            Log.d("CheckoutActivity", "Item: $item")
            totalPrice += item.price * item.quantity
        }
        binding.txtTotal.text = getString(R.string.string_total_price_format, totalPrice)
        binding.btnPay.setOnClickListener {
            RetrofitInstance.api.checkout().enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@CheckoutActivity,
                            "Purchased Successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@CheckoutActivity,
                            "Failed purchase",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(
                        this@CheckoutActivity,
                        "Error: ${t.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })

            finish()
        }
    }
}
