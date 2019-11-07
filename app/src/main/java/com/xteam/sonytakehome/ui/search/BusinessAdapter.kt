package com.xteam.sonytakehome.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xteam.sonytakehome.R
import com.xteam.sonytakehome.databinding.ItemBusinessBinding
import com.xteam.sonytakehome.model.Business


@BindingAdapter("items")
fun setItems(listView: RecyclerView, items: List<Business>) {
    (listView.adapter as BusinessAdapter).submitList(items)
}

/**
 * Adapter to display a list of businesses.
 */
class BusinessAdapter(private val viewModel: SearchViewModel) :
    ListAdapter<Business, BusinessAdapter.ViewHolder>(BusinessDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(viewModel, item)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.clearAnimation()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ItemBusinessBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var lastPosition = -1

        fun bind(viewModel: SearchViewModel, item: Business) {

            binding.viewmodel = viewModel
            binding.business = item

            val animation = AnimationUtils.loadAnimation(
                binding.root.context,
                if (layoutPosition > lastPosition)
                    R.anim.up_from_bottom
                else
                    R.anim.down_from_top
            )
            binding.root.startAnimation(animation)
            lastPosition = layoutPosition

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBusinessBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}


class BusinessDiffCallback : DiffUtil.ItemCallback<Business>() {
    override fun areItemsTheSame(oldItem: Business, newItem: Business): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Business, newItem: Business): Boolean {
        return oldItem == newItem
    }
}