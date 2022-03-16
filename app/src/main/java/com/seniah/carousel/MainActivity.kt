package com.seniah.carousel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val images = listOf(
        "https://live.staticflickr.com/65535/50918737422_389c5125ab_k.jpg",
        "https://live.staticflickr.com/65535/51052509041_2fa12bf941_k.jpg",
        "https://live.staticflickr.com/65535/50894122753_cbe6594cfb_k.jpg"
    )

    private val carouselView: CarouselView by lazy { findViewById(R.id.carousel_view) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carouselView.setImages(
            items = images,
            clickListener = object : CarouselView.OnCarouselClickListener {
                override fun onClick(url: String) {
                    Log.i("Tag", url)
                }
            }
        )
    }
}
