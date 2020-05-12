package com.example.challenge2.utils

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.challenge2.R

class LoadingUtils {
    fun showLoading(context: Context, swipeRefreshLayout: SwipeRefreshLayout) {
        swipeRefreshLayout.setColorSchemeColors(
            ContextCompat.getColor(context,
                R.color.colorPrimary))
        swipeRefreshLayout.isEnabled = true
        swipeRefreshLayout.isRefreshing = true
    }
    fun dismissLoading(swipeRefreshLayout: SwipeRefreshLayout) {
        swipeRefreshLayout.isRefreshing = false
        swipeRefreshLayout.isEnabled = false
    }
}