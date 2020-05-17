package com.example.challenge2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.R
import com.example.challenge2.adapter.NewsAdapter
import com.example.challenge2.data.*
import com.example.challenge2.util.dismissLoading
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_entertainment.*
import org.jetbrains.annotations.Nullable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_entertainment,
            container, false
        )

    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetJob()
    }

    private fun callApiGetJob() {
        val httpClient = httpClient()
        val apiRequest = apiRequest<KasusService>(httpClient, ApiPublicList.NEWS_URL)
        val call = apiRequest.getNews("covid", "5931f1fdfd434065b360549610c4d06b")
        call.enqueue(object : Callback<NewsItem> {
            override fun onFailure(call: Call<NewsItem>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<NewsItem>, response:
                Response<NewsItem>
            ) {
                tampilJob(response.body()!!.articles)
            }
        })
    }

    private fun tampilJob(data: List<ArticlesItem?>?) {
        rv_job.layoutManager = LinearLayoutManager(context!!)
        rv_job.adapter = NewsAdapter(context!! , data)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
