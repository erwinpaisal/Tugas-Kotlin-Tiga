package com.erwin.tugaskotlin3

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ivImageAkun.setOnClickListener {
            checkPermission()
        }

    }

    val READIMAGE: Int = 253
    fun checkPermission() {
        if (Build.VERSION.SDK_INT >=23) {
            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), READIMAGE)
                return
            }
        }
        loadImage()
    }
    override fun onRequestPermissionsResult (requestCode: Int,
                                             permissions: Array<out String>, grantResult: IntArray) {
        when (requestCode) {
            READIMAGE -> {
                if (grantResult[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    loadImage()
                }
                else {
                    Toast.makeText(applicationContext, "gambar tidak dapat di akses", Toast.LENGTH_LONG).show()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode,
                permissions, grantResult)
        }
    }
    val PICK_IMAGE_CODE = 123
    //load Gambar
    fun loadImage() {
        var intent = Intent (
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_CODE && data != null &&
            resultCode == RESULT_OK) {

//SET foto profil
            val selectedImage = data.data
            val filePathColum = arrayOf(MediaStore.Images.Media.DATA)
            val cursor = contentResolver.query(selectedImage,
                filePathColum, null, null, null)
            cursor!!.moveToFirst()
            val coulumIndex = cursor.getColumnIndex(filePathColum[0])
            val picturePath = cursor.getString(coulumIndex)
            cursor.close ()
            ivImageAkun.setImageResource(BitmapFactory.decodeFile(picturePath))
        }
    }
}
