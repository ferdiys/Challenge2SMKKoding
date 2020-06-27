package com.example.challenge2.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challenge2.AddFeeds
import com.example.challenge2.FeedsModel
import com.example.challenge2.R
import com.example.challenge2.adapter.FeedsAdapter
import com.example.challenge2.data.KasusGlobalItem
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.showLoading
import com.example.challenge2.viewmodel.FeedsFragmentViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_feeds.*
import kotlinx.android.synthetic.main.fragment_feeds.view.*
import org.jetbrains.annotations.Nullable
import java.util.ArrayList

class FeedsFragment : Fragment() {
    var dataFeeds: MutableList<FeedsModel> = ArrayList()
    private val viewModel by viewModels<FeedsFragmentViewModel>()
    private var adapter: FeedsAdapter? = null
    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }
    override fun onViewCreated(

        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        init()
        getData()
        viewModel.init(requireContext())
        viewModel.allFeeds.observe(viewLifecycleOwner, Observer { myFeeds ->
            myFeeds?.let { adapter?.setData(it) }
        })

        view.fab.setOnClickListener {
            val intent = Intent(context, AddFeeds::class.java)
            startActivity(intent)
        }
    }

    private fun init(){
            rv_feeds.layoutManager = LinearLayoutManager(context)
            adapter = FeedsAdapter(requireContext(), dataFeeds)
            rv_feeds.adapter = adapter
            dismissLoading(swipeRefreshLayout)

    }

    private fun getData() {
        showLoading(requireContext(), swipeRefreshLayout)
        Toast.makeText(getContext(), "Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show()
        auth = FirebaseAuth.getInstance()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        ref = FirebaseDatabase.getInstance().getReference()
        ref.child(getUserID).child("Feed").addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(getContext(), "Database Error yaa...", Toast.LENGTH_LONG).show()
                dismissLoading(swipeRefreshLayout)
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataFeeds = java.util.ArrayList<FeedsModel>()
                for (snapshot in dataSnapshot.children) {
                    val feed = snapshot.getValue(FeedsModel::class.java)
                    feed?.key = snapshot.key.toString()
                    (dataFeeds as ArrayList<FeedsModel>).add(feed!!)
                }
                viewModel.insertAll(dataFeeds)
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}