package com.example.challenge2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.R
import com.example.challenge2.adapter.WorldAdapter
import com.example.challenge2.data.*
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_provinsi.swipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_world.*
import org.jetbrains.annotations.Nullable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class WorldFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_world, container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetKasus()
    }
    private fun callApiGetKasus() {
        val httpClient = httpClient()
        val apiRequest = apiRequest<KasusService>(httpClient, ApiPublicList.COVID_URL)
        val call = apiRequest.getRegion()
        call.enqueue(object : Callback<List<KasusGlobalItem>> {
            override fun onFailure(call: Call<List<KasusGlobalItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<KasusGlobalItem>>, response:
                Response<List<KasusGlobalItem>>
            ) {
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilWorld(response.body()!!)
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
    private fun tampilWorld(data: List<KasusGlobalItem>) {
        rv_world.layoutManager = LinearLayoutManager(requireContext())
        rv_world.adapter = WorldAdapter(requireContext(), data)
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }


}
