package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.adapter.ProvinsiAdapter
import com.example.challenge2.data.KasusService
import com.example.challenge2.data.ProvinsiItem
import com.example.challenge2.data.apiRequest
import com.example.challenge2.data.httpClient
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.showLoading
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.main.activity_provinsi.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvinsiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provinsi)

        callApiProvinsi()
    }

    private fun callApiProvinsi() {
        showLoading(this, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequest<KasusService>(httpClient)
        val call = apiRequest.getProvinsi()
        call.enqueue(object : Callback<List<ProvinsiItem>> {
            override fun onFailure(call: Call<List<ProvinsiItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<ProvinsiItem>>, response:
                Response<List<ProvinsiItem>>
            ) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilGithubUser(response.body()!!)
                            else -> {
                                tampilToast(this@ProvinsiActivity, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(this@ProvinsiActivity, "Gagal")
                    }
                }
            }
        })
    }

    private fun tampilGithubUser(data: List<ProvinsiItem>) {
        listProvinsi.layoutManager = LinearLayoutManager(this@ProvinsiActivity)
        listProvinsi.adapter = ProvinsiAdapter(this@ProvinsiActivity, data)
    }
}
