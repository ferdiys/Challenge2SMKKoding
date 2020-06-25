package com.example.challenge2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.challenge2.fragments.*

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :

FragmentStateAdapter(fragmentActivity) {
    private val JUMLAH_MENU = 5
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return HomeFragment()
            }
            1 -> {
                return WorldFragment()
            }
            2 -> {
                return InfoFragment()
            }
            3 -> {
                return NewsFragment()
            }
            4 -> {
                return FeedsFragment()
            }
            else -> {
                return HomeFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}

