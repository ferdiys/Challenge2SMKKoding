package com.example.challenge2.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.ProvinsiActivity
import com.example.challenge2.R
import com.example.challenge2.data.KasusGlobalItem
import com.example.challenge2.data.KasusService
import com.example.challenge2.data.apiRequest
import com.example.challenge2.data.httpClient
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.showLoading
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.annotations.Nullable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_home,
            container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        view.tvbtn_detail.setOnClickListener {
            val intent = Intent(context, ProvinsiActivity::class.java)
            startActivity(intent)
        }
    }

    private fun callApiGetKasus() {
        val httpClient = httpClient()
        val apiRequest = apiRequest<KasusService>(httpClient)
        val call = apiRequest.getRegion()
        call.enqueue(object : Callback<List<KasusGlobalItem>> {
            override fun onFailure(call: Call<List<KasusGlobalItem>>, t: Throwable) {

            }
            override fun onResponse(call: Call<List<KasusGlobalItem>>, response:
            Response<List<KasusGlobalItem>>
            ) {
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tv_kasus.text = response.body()!!.toString()
                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }

}
