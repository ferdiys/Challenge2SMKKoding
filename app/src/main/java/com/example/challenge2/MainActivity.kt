package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.example.challenge2.adapter.ViewPagerAdapter
import com.example.challenge2.data.*
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.tampilToast
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_provinsi.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val menuTeks = arrayOf("Kasus","Dunia","Info","Hiburan")
    val menuIcon = arrayOf(R.drawable.icon_virus, R.drawable.world, R.drawable.info, R.drawable.hiburan)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ViewPagerAdapter(this)
        view_pager.setAdapter(adapter);
        TabLayoutMediator(tab_layout, view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = menuTeks[position]
                tab.icon = ResourcesCompat.getDrawable(
                    resources,
                    menuIcon[position], null
                )
            }).attach()
    }

}

