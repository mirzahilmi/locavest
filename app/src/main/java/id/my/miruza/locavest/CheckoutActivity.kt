package id.my.miruza.locavest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.my.miruza.locavest.databinding.ActivityCheckoutBinding

class CheckoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheckoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cartItems: List<CartItem>  = intent.extras?.getParcelableArrayList("cartItems")!!
        var totalPrice = 0F
        for (item: CartItem in cartItems) {
            totalPrice += item.pricePer * item.amount
        }
        binding.txtTotal.text = getString(R.string.string_total_price_format, totalPrice)
    }
}