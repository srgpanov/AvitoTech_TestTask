package com.srgpanov.avitotech.ui.screens.number_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.srgpanov.avitotech.data.DataSource
import com.srgpanov.avitotech.data.NumberItem
import com.srgpanov.avitotech.other.MutableLiveDataKt
import com.srgpanov.avitotech.other.extension.notifyObservers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

class NumberViewModel @Inject constructor(private val dataSource: DataSource) : ViewModel() {
    private val _numbers = MutableLiveDataKt<List<NumberItem>>(mutableListOf())
    val numbers: LiveData<List<NumberItem>>
        get() = _numbers


    init {
        viewModelScope.launch {
            _numbers.value = dataSource.getInitData().toMutableList()
            dataSource.numbers.collect { items: List<NumberItem> ->
                _numbers.value = items
            }
        }
    }


    fun removeItem(numberItem: NumberItem) {
        dataSource.removeItem(numberItem)
    }
}