package com.example.instabus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.instabus.fragments.BusStopListFragment
import com.example.instabus.fragments.BusStopMapFragment
import com.example.instabus.fragments.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var liste = arrayOf("a", "b", "c", "d", "e", "f", "g", "h")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(BusStopListFragment(), getString(R.string.tab_bus_stop_list))
        adapter.addFragment(BusStopMapFragment(), getString(R.string.tab_bus_stop_map))
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)


    }

//    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
//        //alimentation liste d'arrets dans le fragment
//        val linearLayoutStops = findViewById<LinearLayout>(R.id.busStopListLayout)
//        for (stop in liste){
//            val textView =  TextView(this)
//            textView.text = stop
//            textView.layoutParams = LinearLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT,
//                1.0f
//            )
//            textView.textSize = 30F
//            linearLayoutStops.addView(textView)
//        }
//    }
}
