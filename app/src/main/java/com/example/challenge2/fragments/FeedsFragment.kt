package com.example.challenge2.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Delete
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.challenge2.AddFeeds
import com.example.challenge2.FeedsModel
import com.example.challenge2.ProvinsiActivity
import com.example.challenge2.R
import com.example.challenge2.adapter.FeedsAdapter
import com.example.challenge2.util.dismissLoading
import com.example.challenge2.util.showLoading
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_feeds.*
import kotlinx.android.synthetic.main.fragment_feeds.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.annotations.Nullable

class FeedsFragment : Fragment() {
    lateinit var ref : DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var feedData: ArrayList<FeedsModel>
//    lateinit var listTeman : ArrayList<MyFriend>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        view.fab.setOnClickListener {
            val intent = Intent(context, AddFeeds::class.java)
            startActivity(intent)
        }
    }
    private fun getData() {
        //Mendapatkan Referensi Database
        showLoading(context!!, swipeRefreshLayout)
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
                //Inisialisasi ArrayList
                feedData = java.util.ArrayList<FeedsModel>()
                for (snapshot in dataSnapshot.children) {
                    //Mapping data pada DataSnapshot ke dalam objek mahasiswa
                    val feed = snapshot.getValue(FeedsModel::class.java)
                    //Mengambil Primary Key, digunakan untuk proses Update dan Delete
                    feed?.key = snapshot.key.toString()
                    feedData.add(feed!!)
                }
                //Memasang Adapter pada RecyclerView
                rv_feeds.layoutManager = LinearLayoutManager(context)
                rv_feeds.adapter = FeedsAdapter(context!!, feedData)
                Toast.makeText(getContext(), "Data Berhasil Dimuat", Toast.LENGTH_LONG).show()
                dismissLoading(swipeRefreshLayout)
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}