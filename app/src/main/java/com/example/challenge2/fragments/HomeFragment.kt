package com.example.challenge2.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.challenge2.ProvinsiActivity
import com.example.challenge2.R
import com.example.challenge2.data.*
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.main.activity_provinsi.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.annotations.Nullable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_home,
            container, false
        )
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


    private fun getData() {
        val httpClient = httpClient()
        val apiRequest = apiRequest<KasusService>(httpClient)
        val call = apiRequest.getIndo()

        call.enqueue(object : Callback<List<IndoItem>> {
            override fun onFailure(call: Call<List<IndoItem>>, t: Throwable) {
                Toast.makeText(activity!!, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<IndoItem>>,
                response: Response<List<IndoItem>>
            ) {
                tv_kasus.text = response.body()!![0].positif.toString()
                tv_sembuh.text = response.body()!![0].sembuh.toString()
                tv_meninggal.text = response.body()!![0].meninggal.toString()
            }
        })
    }
}

