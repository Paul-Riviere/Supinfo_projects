package com.example.instabus

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class PreviewActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_preview)
        val previewPhoto: Bitmap = intent.extras?.get("previewImage") as Bitmap
        val imageView = findViewById<ImageView>(R.id.previewImage)
        imageView.setImageBitmap(previewPhoto)

        val button = findViewById<Button>(R.id.postPictureButton)
        button.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            startActivity(intent)
        }
    }
}