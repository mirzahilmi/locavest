package id.my.miruza.locavest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import id.my.miruza.locavest.R
import id.my.miruza.locavest.adapter.ViewPagerAdapter

class WelcomeActivity : AppCompatActivity() {
    lateinit var btToLogin : Button
    lateinit var btSkip : Button
    lateinit var viewPager2 : ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page1)

        viewPager2 = findViewById(R.id.viewPager2)
        val adapter = ViewPagerAdapter(this as FragmentActivity)

        viewPager2.adapter = adapter

    }
}

