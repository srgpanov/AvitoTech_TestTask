package com.srgpanov.avitotech.ui.screens.number_list

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.srgpanov.avitotech.other.extension.dp

class Decorator(private val spanCount: Int) : RecyclerView.ItemDecoration() {
    private val defaultMargin = 16.dp
    private val smallMargin = 8.dp

    override fun getItemOffsets(
        rect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val holder = parent.getChildViewHolder(view)
        val position = holder.bindingAdapterPosition
        when {
            position % spanCount == spanCount - 1 -> {//last item in a row
                rect.right = defaultMargin
            }
            position % spanCount == 0 -> {//first item in a row
                rect.left = defaultMargin
                rect.right = smallMargin
            }
            else -> {//middle items in a row
                rect.right = smallMargin
            }
        }
        rect.bottom = smallMargin
    }

}