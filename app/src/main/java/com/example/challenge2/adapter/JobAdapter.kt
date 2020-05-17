package com.example.challenge2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge2.R
import com.example.challenge2.data.JobItem
import kotlinx.android.synthetic.main.card_job.view.*

class JobAdapter(
    private val context: Context,
    private val arrayList: List<JobItem>
) :
    RecyclerView.Adapter<JobAdapter.Holder>() {
    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_job, parent, false)
        )
    }

    override fun getItemCount(): Int = arrayList!!.size


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.tv_company.text = arrayList?.get(position)?.company.toString()
        holder.view.tv_company_url.text = arrayList?.get(position)?.companyUrl.toString()
        holder.view.tv_company_location.text = arrayList?.get(position)?.location.toString()
        holder.view.tv_company_desc.text = arrayList?.get(position)?.description.toString()
        holder.view.tv_title.text = arrayList?.get(position)?.description.toString()

    }
}