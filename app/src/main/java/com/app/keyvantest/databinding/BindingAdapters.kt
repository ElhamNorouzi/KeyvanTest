package com.app.keyvantest.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.keyvantest.utils.Constants
import com.squareup.picasso.Picasso

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(v: ImageView?, url: String?) {
        Picasso.get().load("${Constants.IMAGE_BASE_URL}$url").into(v)
    }
}