package com.srgpanov.avitotech.other.extension

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData


val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Float.dp: Float
    get() = this * Resources.getSystem().displayMetrics.density

fun <T> MutableLiveData<T>.notifyObservers() {
    postValue(value)
}
