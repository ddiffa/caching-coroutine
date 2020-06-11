package com.hellodiffa.caching_coroutinexworker.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hellodiffa.caching_coroutinexworker.R

@BindingAdapter("loadImage")
fun loadImage(imgView: ImageView, url: String) {

    Glide.with(imgView.context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(imgView)
}