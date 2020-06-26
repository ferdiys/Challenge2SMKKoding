package com.example.challenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_edit_feeds.*

class EditFeeds : AppCompatActivity() {
    private var newTitle: EditText? = null
    private var newCaption: EditText? = null
    private var newDate: TextView? = null
    private var ref: DatabaseReference? = null
    private var auth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_feeds)

        getData()

        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();

        newTitle = findViewById(R.id.edt_title_new)
        newCaption = findViewById(R.id.edt_caption_new)
        newDate = findViewById(R.id.tv_date_new)

        btn_save.setOnClickListener {
            val cekTitle = newTitle?.getText().toString()
            val cekCaption = newCaption?.getText().toString()
            val cekDate = newDate?.getText().toString()

            if (cekCaption.isEmpty() && cekTitle.isEmpty() && cekDate.isEmpty()) {
                Toast.makeText(this, "Data tidak boleh ada yang kosong",
                    Toast.LENGTH_SHORT).show();
            } else {
                val newFeed = FeedsModel(cekTitle, cekCaption, cekDate, null)
                val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
                val getKey: String = getIntent().getStringExtra("getPrimaryKey").toString()
                ref!!.child(getUserID).child("Feed")
                    .child(getKey).setValue(newFeed)
                    .addOnCompleteListener {
                        Toast.makeText(this, "Data Berhasil Disimpan",
                            Toast.LENGTH_SHORT).show()
                        finish();
                    }
            }
        }

    }
    private fun getData() {
        val getTitle: String = getIntent().getStringExtra("title").toString()
        val getCaption: String = getIntent().getStringExtra("caption").toString()
        val getDate: String = getIntent().getStringExtra("date").toString()
        edt_title_new.setText(getTitle)
        edt_caption_new.setText(getCaption)
        tv_date_new.setText(getDate)
    }

}