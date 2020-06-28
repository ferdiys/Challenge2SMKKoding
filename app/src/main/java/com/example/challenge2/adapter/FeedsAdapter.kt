package com.example.challenge2.adapter

import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge2.EditFeeds
import com.example.challenge2.FeedsModel
import com.example.challenge2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.card_feed.view.*
import kotlinx.android.synthetic.main.fragment_feeds.view.*


class FeedsAdapter(
    private val context: Context,
    private var items: List<FeedsModel>,
    private var positiion: Int = -1

) :
    RecyclerView.Adapter<FeedsAdapter.ViewHolder>() {
    var listener: dataListener? = null
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

        holder.itemView.setOnLongClickListener(OnLongClickListener { view ->
            val action = arrayOf("Update", "Delete")
            val alert = AlertDialog.Builder(view.context)
            alert.setItems(action) { dialog, i ->
                when (i) {
                    0 -> {
                        val bundle = Bundle()
                        bundle.putString("title", items.get(position)?.title)
                        bundle.putString("caption", items.get(position)?.caption)
                        bundle.putString("date", items.get(position)?.date)
                        bundle.putString(
                            "getPrimaryKey", items.get(position)?.key
                        )
                        val intent =
                            Intent(view.context, EditFeeds::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }
                    1 -> {
                        val auth = FirebaseAuth.getInstance()
                        val ref = FirebaseDatabase.getInstance().getReference()
                        val getUserID: String =
                            auth?.getCurrentUser()?.getUid().toString()
                        if (ref != null) {
                            ref.child(getUserID)
                                .child("Feed")
                                .child(items.get(position)?.key.toString())
                                .removeValue()
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        context, "Data Berhasil Dihapus",
                                        Toast.LENGTH_SHORT
                                    ).show()
//                                    viewModel.delete(data)
                                }
                        }

                    }
                }
            }
            alert.create()
            alert.show()
            true
        })

    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: FeedsModel?) {
            itemView.tv_title.text = item!!.title
            itemView.tv_caption.text = item!!.caption
            itemView.tv_date.text = item!!.date

        }

    }

    fun setData(list: List<FeedsModel>) {
        this.items = list
    }
    interface dataListener{
        fun onDeleteData(data: FeedsModel, position: Int)
    }
}