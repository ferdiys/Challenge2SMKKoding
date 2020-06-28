package com.example.challenge2.fragments

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.challenge2.ProvinsiActivity
import com.example.challenge2.R
import com.example.challenge2.data.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.annotations.Nullable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


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
        getUser()
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        val currentDate = date.format(Date())
        view.tv_date.setText(currentDate)
    }


    private fun getData() {
        val httpClient = httpClient()
        val apiRequest = apiRequest<KasusService>(httpClient, ApiPublicList.COVID_URL)
        val call = apiRequest.getIndo()

        call.enqueue(object : Callback<List<IndoItem>> {
            override fun onFailure(call: Call<List<IndoItem>>, t: Throwable) {
                Toast.makeText(context!!, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<List<IndoItem>>,
                response: Response<List<IndoItem>>
            ) {
                tv_kasus.text = response.body()!![0].positif.toString()
                tv_sembuh.text = response.body()!![0].sembuh.toString()
                tv_meninggal.text = response.body()!![0].meninggal.toString()
                tv_nasional.text = response.body()!![0].name.toString()
            }
        })
    }
    private fun getUser(){
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            user?.let {
                for (profile in it.providerData) {
                    val name = user.displayName
                    tv_username.text = name.toString()
                }
            }
        } else {
            // No user is signed in
        }
    }
}

