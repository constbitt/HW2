package ru.hse.software.construction.visitor.commands

import ru.hse.software.construction.`interface`.output.OutputHelper
import ru.hse.software.construction.models.OrderStatus
import ru.hse.software.construction.models.User
import ru.hse.software.construction.parsers.ReviewParser
import ru.hse.software.construction.repository.DataManager

class PayOrderCommand(private val visitor: User): OrderCommand {
    override fun execute() {
        val order = DataManager.orders.find { it.visitor == visitor && it.status == OrderStatus.READY }
        if (order == null) {
            println("No orders to pay or review.")
        } else {
            DataManager.totalRevenue.plus(order.items.sumOf { it.price })
            println("Order paid successfully.")
            order.reviews  = ReviewParser(order).parse()
            order.status = OrderStatus.COMPLETED
        }
        OutputHelper().pressKeyToContinue()
    }
}