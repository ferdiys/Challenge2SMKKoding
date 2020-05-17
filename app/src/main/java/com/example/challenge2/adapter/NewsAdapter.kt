package com.example.challenge2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge2.R
import com.example.challenge2.data.ArticlesItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_news.view.*

class NewsAdapter(
    private val context: Context, private val items:
    List<ArticlesItem?>?
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context, LayoutInflater.from(context).inflate(
                R.layout.card_news,
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
        fun bindItem(item: ArticlesItem?) {
            itemView.tv_title.text = item!!.title
            itemView.tv_desc.text = item!!.description
            itemView.tv_url.text = item!!.url
            Glide.with(context).load(item!!.urlToImage).into(itemView.img_news)
        }
    }
}