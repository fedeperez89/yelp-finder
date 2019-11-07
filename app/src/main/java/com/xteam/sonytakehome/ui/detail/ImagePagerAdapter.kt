package com.xteam.sonytakehome.ui.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.squareup.picasso.Picasso
import com.xteam.sonytakehome.R


@BindingAdapter("app:pagerItems")
fun setPagerItems(pager: ViewPager, items: List<String>?) {
    items?.let {
        (pager.adapter as ImagePagerAdapter).submitItems(it)
    }
}

/**
 * Adapter to create a simple gallery of photos.
 */
class ImagePagerAdapter constructor(
    context: Context,
    private val items: MutableList<String>
) :
    PagerAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageLayout = inflater.inflate(R.layout.item_view_pager, container, false)!!

        val imageView = imageLayout
            .findViewById<ImageView>(R.id.image_page)

        Picasso.get().load(items[position]).apply {
            fit()
            centerCrop()
            noFade()
            placeholder(R.drawable.yelp_logo)
            into(imageView)
        }

        container.addView(imageLayout, 0)

        return imageLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }


    override fun isViewFromObject(view: View, ob: Any): Boolean {
        return view == ob
    }

    override fun getCount() = items.size


    fun submitItems(newItems: List<String>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}