package com.example.challenge2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.R
import com.example.challenge2.adapter.JobAdapter
import com.example.challenge2.data.*
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_entertainment.*
import org.jetbrains.annotations.Nullable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntertainmentFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callApiGetJob()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_entertainment,
            container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun callApiGetJob() {
        val httpClient = httpClient()
        val apiRequest = apiRequest<KasusService>(httpClient, ApiPublicList.JOB_URL)
        val call = apiRequest.getJob(
            "indonesia"
        )
        call.enqueue(object : Callback<List<JobItem>> {
            override fun onFailure(call: Call<List<JobItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }

            override fun onResponse(
                call: Call<List<JobItem>>, response:
                Response<List<JobItem>>
            ) {
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilJob(response.body()!!)
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
    private fun tampilJob(job: List<JobItem>) {
        rv_job.layoutManager = LinearLayoutManager(context!!)
        rv_job.adapter = JobAdapter(context!!, job)
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
