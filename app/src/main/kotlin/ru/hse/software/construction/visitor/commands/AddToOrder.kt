package ru.hse.software.construction.visitor.commands

import ru.hse.software.construction.`interface`.output.OutputHelper
import ru.hse.software.construction.models.OrderStatus
import ru.hse.software.construction.models.User
import ru.hse.software.construction.parsers.OrderParser
import ru.hse.software.construction.repository.DataManager

class AddToOrderCommand(private val visitor: User): OrderCommand {
    override fun execute() {
        DataManager.orders.find { it.visitor == visitor && it.status in setOf(OrderStatus.CREATED, OrderStatus.ACCEPTED, OrderStatus.IN_PROGRESS) }?.let { currentOrder ->
            val dishesToAdd = OrderParser().parse().filter { dish ->
                DataManager.menu.find { it.name == dish.name && it.quantity > 0 }?.let {
                    it.quantity--
                    true
                } ?: false
            }
            currentOrder.items.addAll(dishesToAdd)
            if (dishesToAdd.isNotEmpty()) {
                println("Dishes added to the order successfully.")
                println("Your order:")
                currentOrder.items.forEachIndexed { index, dish ->
                    println("\t${index + 1}. ${dish.name} - ${dish.price}")
                }
                currentOrder.status = if (currentOrder.status != OrderStatus.IN_PROGRESS) OrderStatus.ACCEPTED else OrderStatus.IN_PROGRESS
            }
        } ?: println("You can't add to a ready order.")
        OutputHelper().pressKeyToContinue()
    }
}