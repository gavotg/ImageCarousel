package com.seniah.carousel

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class CarouselView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val viewPager: ViewPager2 by lazy { findViewById(R.id.carousel_view_pager) }
    private val indicator: TabLayout by lazy { findViewById(R.id.carousel_indicator) }

    init {
        inflate(context, R.layout.carousel, this)
    }

    fun setImages(items: List<String>, clickListener: OnCarouselClickListener) {
        viewPager.adapter = CarouselAdapter(items, clickListener)
        TabLayoutMediator(indicator, viewPager) { _, _ -> }.attach()
    }

    inner class CarouselAdapter(
        private val list: List<String>,
        private val clickListener: OnCarouselClickListener
    ) : RecyclerView.Adapter<CarouselAdapter.ModelViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder =
            ModelViewHolder(
                LayoutInflater.from(context).inflate(R.layout.carousel_item, parent, false),
            )

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
            val imageUrl = list[position]
            holder.imageView.setOnClickListener { clickListener.onClick(imageUrl) }
            Glide.with(context)
                .load(imageUrl)
                .into(holder.imageView)
        }

        inner class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.carousel_image)
        }
    }

    interface OnCarouselClickListener {
        fun onClick(url: String)
    }
}
