package com.example.instabus

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.instabus.data.BusStop
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val button = findViewById<Button>(R.id.takePictureButton)
        button.setOnClickListener { takePicture() }

        val selectedStop: BusStop = intent.extras?.get("selectedStop") as BusStop
        Log.d("selectedStop", selectedStop.street_name)
        val nameTextView = findViewById<TextView>(R.id.selectedStopName)
        nameTextView?.setText(selectedStop.street_name)
    }

    val REQUEST_IMAGE_CAPTURE = 1

    private fun takePicture() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data.extras?.get("data") as Bitmap
            val intent = Intent(this, PreviewActivity::class.java)
            //utile pour récupérer l'id de l'arret dans le layout de preview pour enregistrement en base
//            intent.putExtra("selectedStop", selectedStop as Serializable)
            intent.putExtra("previewImage", imageBitmap)
            startActivity(intent)
        }
    }

}
