package com.srgpanov.avitotech.other

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding




val ViewBinding.context: Context
    get() = this.root.context

inline fun <VB : ViewBinding> ViewGroup.inflateBinding(
    creator: (inflater: LayoutInflater, root: ViewGroup, attachToRoot: Boolean) -> VB,
    attachToRoot: Boolean = false
): VB {
    return creator.invoke(this.context.layoutInflater, this, attachToRoot)
}
