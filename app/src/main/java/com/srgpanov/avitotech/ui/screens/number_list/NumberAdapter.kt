package com.srgpanov.avitotech.ui.screens.number_list

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.srgpanov.avitotech.data.NumberItem
import com.srgpanov.avitotech.databinding.ItemNumberBinding
import com.srgpanov.avitotech.other.inflateBinding

class NumberAdapter() :
    ListAdapter<NumberItem, NumberAdapter.NumberHolder>(NumberComparator) {

    var listener: ((binding: ItemNumberBinding, item: NumberItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NumberHolder.create(parent)

    override fun onBindViewHolder(holder: NumberHolder, position: Int) {
        val item: NumberItem = getItem(position)
        holder.bind(item)
        listener?.invoke(holder.binding, item)
    }

    class NumberHolder(val binding: ItemNumberBinding) :RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(parent: ViewGroup): NumberHolder {
                val binding = parent.inflateBinding(ItemNumberBinding::inflate)
                return NumberHolder(binding)
            }
        }

        fun bind(item: NumberItem) {
            binding.tv.text = item.count.toString()
        }
    }

}