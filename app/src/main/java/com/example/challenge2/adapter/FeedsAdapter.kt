package com.example.challenge2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge2.FeedsModel
import com.example.challenge2.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_feed.view.*
import kotlinx.android.synthetic.main.card_news.view.*

class FeedsAdapter(
    private val context: Context,
    private val items: List<FeedsModel?>?
) :
    RecyclerView.Adapter<FeedsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context, LayoutInflater.from(context).inflate(
                R.layout.card_feed,
                parent, false
            )
        )

    override fun getItemCount(): Int {
        return items!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items!!.get(position))
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: FeedsModel?) {
            itemView.tv_caption.text = item!!.caption
            itemView.tv_location.text = item!!.location
        }
    }
}