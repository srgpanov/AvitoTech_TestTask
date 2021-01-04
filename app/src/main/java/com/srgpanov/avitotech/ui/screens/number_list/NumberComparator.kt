package com.srgpanov.avitotech.ui.screens.number_list

import androidx.recyclerview.widget.DiffUtil
import com.srgpanov.avitotech.data.NumberItem

object NumberComparator:DiffUtil.ItemCallback<NumberItem>() {
    override fun areItemsTheSame(oldItem: NumberItem, newItem: NumberItem): Boolean {
        return oldItem.count==newItem.count
    }

    override fun areContentsTheSame(oldItem: NumberItem, newItem: NumberItem): Boolean {
        return oldItem==newItem
    }
}