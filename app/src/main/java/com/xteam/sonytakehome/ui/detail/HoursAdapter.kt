package com.xteam.sonytakehome.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xteam.sonytakehome.R
import com.xteam.sonytakehome.databinding.ItemHourBinding
import com.xteam.sonytakehome.model.Open
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat


@BindingAdapter("hourItems")
fun setItems(listView: RecyclerView, items: List<Open>?) {
    items?.let {
        (listView.adapter as HoursAdapter).submitList(it)
    }
}

@BindingAdapter("hourDay")
fun setHours(view: TextView, day: Int) {
    view.text = DateFormatSymbols.getInstance().weekdays[day + 1]
}

@BindingAdapter("hourFormat")
fun setHoursFormat(view: TextView, open: Open) {
    val displayFormat = SimpleDateFormat("hh:mm a")
    val parseFormat = SimpleDateFormat("HHmm")
    val to = parseFormat.parse(open.start)
    val from = parseFormat.parse(open.end)

    if (to != null && from != null) {
        view.text = view.context.getString(
            R.string.from_to,
            displayFormat.format(to),
            displayFormat.format(from)
        )
    }

}

/**
 * Adapter to show operating hours.
 */
class HoursAdapter :
    ListAdapter<Open, HoursAdapter.ViewHolder>(HoursDiffCallBack()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemHourBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Open) {
            binding.open = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHourBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}


class HoursDiffCallBack : DiffUtil.ItemCallback<Open>() {
    override fun areItemsTheSame(oldItem: Open, newItem: Open): Boolean {
        return oldItem.day == newItem.day
    }

    override fun areContentsTheSame(oldItem: Open, newItem: Open): Boolean {
        return oldItem == newItem
    }
}