package com.example.instabus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instabus.fragments.BusStopListFragment
import com.example.instabus.fragments.BusStopMapFragment
import com.example.instabus.fragments.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
}
