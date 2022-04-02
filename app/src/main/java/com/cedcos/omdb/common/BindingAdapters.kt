package com.cedcos.omdb.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

/**
 * Created by Upendra on 19/2/2022.
 */

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()&&!url.contentEquals("http")) return
    Glide.with(this).load(url)
        .apply(
            RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
        )
            .into(this)
}
