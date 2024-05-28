package id.my.miruza.locavest.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.my.miruza.locavest.fragment.CartFragment
import id.my.miruza.locavest.data.CartItem
import id.my.miruza.locavest.fragment.FoodstuffFragment
import id.my.miruza.locavest.R
import id.my.miruza.locavest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var cartItems: List<CartItem>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodstuffFg = FoodstuffFragment()
        val cartFg = CartFragment()

        setCurrentFragment(foodstuffFg)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.foodstuff -> setCurrentFragment(foodstuffFg)
                R.id.cart -> {
                    setCurrentFragment(cartFg, true)
                    binding.btnContainer.visibility = View.VISIBLE
                }
            }
            true
        }

        binding.btnCheckout.setOnClickListener {
            val intent = Intent(this, CheckoutActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelableArrayList("cartItems", ArrayList(cartItems!!))
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    private fun setCurrentFragment(fr: Fragment, addToStack: Boolean = false) =
        supportFragmentManager.beginTransaction().apply {
            replace(binding.frameLayout.id, fr)
            if (addToStack) {
                addToBackStack(null)
            }
            commit()
        }

    fun setCartItems(items: List<CartItem>) {
        cartItems = items
    }
}