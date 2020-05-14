package com.example.challenge2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge2.R
import com.example.challenge2.data.ProvinsiItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_daerah.view.*

class ProvinsiAdapter(
    private val context: Context,
    private val arrayList: List<ProvinsiItem>
) :
    RecyclerView.Adapter<ProvinsiAdapter.Holder>() {
    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_daerah, parent, false)
        )
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.txtKasus.text = arrayList?.get(position)?.data!!.kasusPosi.toString()
        holder.view.txtSembuh.text = arrayList?.get(position)?.data!!.kasusSemb.toString()
        holder.view.txtMeninggal.text = arrayList?.get(position)?.data!!.kasusMeni.toString()
        holder.view.tv_provinsi.text = arrayList?.get(position)?.data!!.provinsi.toString()

    }
}
