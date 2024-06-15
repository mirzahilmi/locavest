package id.my.miruza.locavest.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.my.miruza.locavest.fragment.CategoriesFragment
import id.my.miruza.locavest.fragment.CartFragment
import id.my.miruza.locavest.R
import id.my.miruza.locavest.databinding.ActivityMainBinding
import id.my.miruza.locavest.fragment.ProfileFragment
import id.my.miruza.locavest.fragment.ProfileHolderFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodstuffFg = CategoriesFragment()
        val cartFg = CartFragment()
        val profileFg = ProfileHolderFragment()
        setCurrentFragment(foodstuffFg)
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.foodstuff -> setCurrentFragment(foodstuffFg)
                R.id.cart -> setCurrentFragment(cartFg, true)
                R.id.profile -> setCurrentFragment(profileFg, true)
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