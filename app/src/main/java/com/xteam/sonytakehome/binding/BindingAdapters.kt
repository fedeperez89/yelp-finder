/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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