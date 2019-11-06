package com.xteam.sonytakehome.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.xteam.sonytakehome.R


@BindingAdapter("visibleGone")
fun showHide(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("picassoImage")
fun loadImage(view: ImageView, picassoImage: String?) {
    picassoImage?.let {
        if (it.isBlank()) {
            Picasso.get().load(R.drawable.yelp_logo).apply {
                fit()
                centerCrop()
                into(view)
            }
        } else {
            Picasso.get().load(it).apply {
                fit()
                centerCrop()
                placeholder(R.drawable.yelp_logo)
                into(view)
            }

        }
    }
}