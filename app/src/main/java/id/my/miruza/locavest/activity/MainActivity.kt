package id.my.miruza.locavest.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.my.miruza.locavest.fragment.CategoriesFragment
import id.my.miruza.locavest.fragment.CartFragment
import id.my.miruza.locavest.R
import id.my.miruza.locavest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodstuffFg = CategoriesFragment()
        val cartFg = CartFragment()
        setCurrentFragment(foodstuffFg)
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.foodstuff -> setCurrentFragment(foodstuffFg)
                R.id.cart -> setCurrentFragment(cartFg, true)
            }
            true
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
}