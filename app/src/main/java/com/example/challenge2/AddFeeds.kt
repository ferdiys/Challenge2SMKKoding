package com.example.challenge2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.challenge2.viewmodel.FeedsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_feeds.*
import java.text.SimpleDateFormat
import java.util.*


class AddFeeds : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    private var auth: FirebaseAuth? = null
    private val viewModel by viewModels<FeedsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_feeds)
        ref = FirebaseDatabase.getInstance().getReference()
        auth = FirebaseAuth.getInstance()
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
        val currentDate = date.format(Date())
        tv_date.setText(currentDate)


        btn_save.setOnClickListener {
            prosesSave()
        }

    }

    private fun prosesSave() {
        val getTitle: String = edt_title?.getText().toString()
        val getCaption: String = edt_caption?.getText().toString()
        val getDate: String = tv_date?.getText().toString()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        val feed = FeedsModel(getTitle, getCaption, getDate, "")

        if (getCaption.isEmpty() && getTitle.isEmpty() && getDate.isEmpty()) {
            Toast.makeText(this@AddFeeds, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT)
                .show()
        } else {

            ref.child(getUserID).child("Feed").push().setValue(feed)
                .addOnCompleteListener {
                    Toast.makeText(
                        this, "Data Berhasil Disimpan",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.addData(feed)

                }
            finish()
        }
    }
}
