package com.srgpanov.avitotech.other

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat


fun Context.getColorCompat(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)