package com.aryanbaral1100.memeshareapp

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        load()
    }
    private fun load() {
        var PrograssBar = findViewById<ProgressBar>(R.id.progressBar);
        PrograssBar.visibility = View.VISIBLE;
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme"

// Request a string response from the provided URL.
        val stringRequest = JsonObjectRequest(url, { response ->
            // Display the first 500 characters of the response string
            val url = response.getString("url")
            val imageView = findViewById<ImageView>(R.id.imageView)
            Glide.with(this).load(url).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            }).into(imageView)
            PrograssBar.visibility = View.GONE;
        }
        ) { Toast.makeText(this, "Something went Wrong", Toast.LENGTH_LONG).show() }

// Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(stringRequest)
    }

    fun ShareOnClick(view: View) {
        var intent = Intent(Intent.ACTION_SEND);
        intent.type = "text/plain"
        var chooser = Intent.createChooser(intent,"Share this using ")
        startActivity(chooser)
        
    }

    fun NextOnClick(view: View) {
        load()

    }

}