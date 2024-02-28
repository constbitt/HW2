package ru.hse.software.construction.kitchen

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.hse.software.construction.models.Order
import ru.hse.software.construction.models.OrderStatus
import ru.hse.software.construction.repository.DataManager
import java.util.ArrayList

interface Kitchen {
    fun cookOrders()
}

@OptIn(DelicateCoroutinesApi::class)
object KitchenService: Kitchen {
    private var isRestaurantOpen = true

    init {
        GlobalScope.launch {
            cookOrders()
        }
    }

    override fun cookOrders() {
        while (isRestaurantOpen) {
            val order = fastestOrder()
            if (order != null) {
                cookOrder(order)
            }
            Thread.sleep(1000)
        }
    }

    private fun cookOrder(order: Order) {
        synchronized(DataManager.orders) {
            val orderInDataManager = DataManager.orders.find { it == order && it.items == order.items && it.status == order.status }
            if (orderInDataManager != null && orderInDataManager.status == OrderStatus.ACCEPTED) {
                val itemsToProcess = ArrayList(orderInDataManager.items)
                orderInDataManager.status = OrderStatus.IN_PROGRESS
                for (dish in itemsToProcess) {
                    if (orderInDataManager.status == OrderStatus.CANCELLED) {
                        break
                    }
                    if (dish.preparationTime > 0) {
                        Thread.sleep(dish.preparationTime * 1000L)
                    }
                }
                if (orderInDataManager.status != OrderStatus.CANCELLED) {
                    orderInDataManager.status = OrderStatus.READY
                }
            }
        }
    }

    private fun fastestOrder(): Order? {
        return DataManager.orders.filter { it.status == OrderStatus.ACCEPTED }.minByOrNull { orderPreparationTime(it) }
    }

    private fun orderPreparationTime(order: Order): Int {
        var totalTime = 0
        for (dish in order.items) {
            totalTime += dish.preparationTime
        }
        return totalTime
    }
}
