package ru.hse.software.construction.`interface`.output

import ru.hse.software.construction.`interface`.menu.Page
import ru.hse.software.construction.models.OrderStatus
import ru.hse.software.construction.repository.DataManager

class OrdersPage: Page {
    override fun load() {
        println("Order Information Board:")
        println("+-----------+--------------+")
        println("| Order ID  |   Status     |")
        println("+-----------+--------------+")
        DataManager.orders.filter { it.status != OrderStatus.COMPLETED && it.status != OrderStatus.CANCELLED }
            .forEach { order ->
                println("| ${(DataManager.orders.indexOf(order) + 1).toString().padEnd(9)} | ${order.status.toString().padStart(12)} |")
            }
        println("+-----------+--------------+")
        OutputHelper().pressKeyToContinue()
    }
}