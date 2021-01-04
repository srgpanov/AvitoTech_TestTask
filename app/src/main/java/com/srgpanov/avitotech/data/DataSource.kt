package com.srgpanov.avitotech.data

import com.srgpanov.avitotech.other.extension.notifyObservers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext
import kotlin.random.Random

@Singleton
class DataSource @Inject constructor() : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    private var nextItem = INIT_DATA_SIZE
    private val deletedPool: MutableSet<Int> = HashSet()

    private var _numbers: MutableStateFlow<List<NumberItem>> = MutableStateFlow(getInitData())
    val numbers: StateFlow<List<NumberItem>>
        get() = _numbers
    var removeJob:Job?=null


    companion object {
        const val NEW_DATA_DELAY = 5_000L
        const val READ_CURRENT_DATA_DELAY = 1_000L
        private const val INIT_DATA_SIZE = 15
    }

    init {
        startNumberProviding()
    }

    private fun startNumberProviding() {
        launch {
            while (true) {
                delay(NEW_DATA_DELAY)
                val numberItem = NumberItem(getNextItem())
                val newList = _numbers.value.toMutableList()
                newList.add(getRandomIndex(), numberItem)
                _numbers.value = newList
            }
        }
    }

    private fun getNextItem() = if (deletedPool.isEmpty()) {
        nextItem++
    } else {
        val itemFromPool = deletedPool.first()
        deletedPool.remove(itemFromPool)
        itemFromPool
    }

    fun getInitData(): List<NumberItem> {
        return List(INIT_DATA_SIZE) { index: Int -> NumberItem(index) }
    }

    private fun getRandomIndex(): Int {
        return if (_numbers.value.isEmpty()) {
            0
        } else {
            Random.nextInt(0, _numbers.value.size)
        }
    }

    fun removeItem(numberItem: NumberItem) {
        if (removeJob?.isActive==true) return
        removeJob= launch {
            val list = _numbers.value.toMutableList()
            val wasRemoved = list.remove(numberItem)
            if (wasRemoved) {
                deletedPool.add(numberItem.count)
                _numbers.value = list
            }
        }
    }


}