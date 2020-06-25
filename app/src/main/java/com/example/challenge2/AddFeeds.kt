package com.example.challenge2

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.android.synthetic.main.activity_add_feeds.*


class AddFeeds : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    private var auth: FirebaseAuth? = null
    lateinit var storageRef : StorageReference
    private var imgPath: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ref = FirebaseDatabase.getInstance().getReference()
        storageRef = FirebaseStorage.getInstance().getReference("Feed")
        auth = FirebaseAuth.getInstance()

        setContentView(R.layout.activity_add_feeds)
        btn_simpan.setOnClickListener{
            prosesSave()
        }
        btn_camera.setOnClickListener {
            var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            startActivityForResult(i, 123)
        }

        btn_galery.setOnClickListener{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE);

                    requestPermissions(permissions,PERMISSION_CODE);
                }
                else{
                    pickImageFromGallery()
                }
            }
            else{
                pickImageFromGallery()
            }
        }

    }

    private fun prosesSave() {
        val getCaption: String = edt_caption?.getText().toString()
        val getLocation: String = edt_location?.getText().toString()
        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
        val dumimage: Uri? = null

        if (getCaption.isEmpty() && getLocation.isEmpty()) {
            Toast.makeText(this@AddFeeds, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT)
                .show()
        } else {
            val feed = FeedsModel(getCaption, getLocation, dumimage, null)
            //struktur databasenya adalah: ID >> Teman >> Key >> Data
            storageRef.putFile(imgPath!!)
                .addOnSuccessListener {
                    storageRef.downloadUrl.addOnSuccessListener {
                        val downloadURL: String = dumimage.toString()
                        val feed = FeedsModel(getCaption, getLocation, dumimage, null)
                        ref.child(getUserID).child("Feed").push().setValue(feed)
                            .addOnCompleteListener {
                                Toast.makeText(
                                    this, "Data Berhasil Disimpan",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        finish()
                    }
                        .addOnFailureListener {
                            println("Info File : ${it.message}")
                        }
                }
        }
    }
    private fun pickImageFromGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICKCODE)
    }

    companion object {
        private val IMAGE_PICKCODE = 1000
        private val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray ){
        when (requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }
                else{
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICKCODE){
            imgPath = data?.data
            iv_feed.setImageURI(data?.data)
        }

        if (requestCode==123)
        {
            var bmp= data?.extras?.get("data") as? Bitmap
            iv_feed.setImageBitmap(bmp)
        }
    }
//    private fun uploadImage() {
//        //Mendapatkan data dari ImageView sebagai Bytes
//        ImageContainer.setDrawingCacheEnabled(true)
//        ImageContainer.buildDrawingCache()
//        val bitmap = (ImageContainer.getDrawable() as BitmapDrawable).bitmap
//        val stream = ByteArrayOutputStream()
//
//        //Mengkompress bitmap menjadi JPG dengan kualitas gambar 100%
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
//        val bytes: ByteArray = stream.toByteArray()
//        val namaFile: String =
//            UUID.randomUUID().toString() + ".jpg" //Nama Gambar (Secara Random)
//        val pathImage =
//            "gambar/$namaFile" //Lokasi lengkap dimana gambar akan disimpan
//        val uploadTask: UploadTask = reference.child(pathImage).putBytes(bytes)
//        uploadTask.addOnCompleteListener(object :
//            OnCompleteListener<UploadTask.TaskSnapshot?> {
//            fun onComplete(task: Task<UploadTask.TaskSnapshot?>) {
//                if (task.isSuccessful()) {
//                    //Mendapatkan URL download dari gambar yang telah disimpan pada Storage
//                    reference.child(pathImage).getDownloadUrl()
//                        .addOnSuccessListener(OnSuccessListener<Uri> { uri -> //Menyimpan URL pada Variable String
//                            val downloadURL = uri.toString()
//
//                            //Menentukan referensi lokasi data url yang akan disimpan
//                            databaseReference.child("gambar").push()
//                                .setValue(DataModels(downloadURL))
//                            progressBar.setVisibility(View.GONE)
//                            Toast.makeText(
//                                this@MainActivity,
//                                "Uploading Berhasil",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                            ImageContainer.setVisibility(View.GONE)
//                        })
//                } else {
//                    progressBar.setVisibility(View.GONE)
//                    Toast.makeText(this@MainActivity, "Uploading Gagal", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
//            .addOnProgressListener { taskSnapshot -> //Method ini digunakan untuk menghitung persentase proses uploading file
//                progressBar.setVisibility(View.VISIBLE)
//                val progress =
//                    100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
//                progressBar.setProgress(progress.toInt())
//            }
//    }
}