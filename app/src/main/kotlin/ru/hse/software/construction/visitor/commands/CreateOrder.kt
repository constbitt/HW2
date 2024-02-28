package ru.hse.software.construction.visitor.commands

import ru.hse.software.construction.admin.commands.Command
import ru.hse.software.construction.models.Order
import ru.hse.software.construction.models.OrderStatus
import ru.hse.software.construction.models.User
import ru.hse.software.construction.repository.DataManager

interface OrderCommand: Command {
    override fun execute()
}

class CreateOrderCommand(private val visitor: User): OrderCommand {
    override fun execute() {
        if (DataManager.orders.any { it.visitor == visitor && it.status != OrderStatus.COMPLETED && it.status != OrderStatus.CANCELLED }) {
            println("You already have one pending order.")
        } else {
            val order = Order(visitor)
            DataManager.orders.add(order)
            println("Order № ${DataManager.orders.size} created successfully.")
        }
    }
}