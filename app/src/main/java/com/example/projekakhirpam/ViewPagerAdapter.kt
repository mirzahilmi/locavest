package com.example.projekakhirpam

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FirstScreenFragment()
            1 -> SecondScreenFragment()
            2 -> ThirdScreenFragment()
            else -> FirstScreenFragment()
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}
