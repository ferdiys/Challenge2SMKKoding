package com.example.challenge2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.challenge2.fragments.BantuanFragment
import com.example.challenge2.fragments.EntertainmentFragment
import com.example.challenge2.fragments.HomeFragment
import com.example.challenge2.fragments.InfoFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :

FragmentStateAdapter(fragmentActivity) {
    private val JUMLAH_MENU = 4
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return HomeFragment()
            }
            1 -> {
                return InfoFragment()

            }
            2 -> {
                return BantuanFragment()
            }
            3 -> {
                return EntertainmentFragment()
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

