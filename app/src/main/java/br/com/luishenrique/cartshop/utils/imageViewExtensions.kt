package br.com.luishenrique.cartshop.utils

import android.content.Context
import android.widget.ImageView
import br.com.luishenrique.cartshop.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.render(uri: String, context: Context) {
    Glide.with(context)
        .load(uri)
        .placeholder(R.drawable.ic_loading_image)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}