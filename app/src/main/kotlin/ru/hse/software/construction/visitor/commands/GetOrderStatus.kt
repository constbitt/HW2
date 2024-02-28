package ru.hse.software.construction.visitor.commands

import ru.hse.software.construction.`interface`.output.OutputHelper
import ru.hse.software.construction.models.OrderStatus
import ru.hse.software.construction.models.User
import ru.hse.software.construction.repository.DataManager

class GetOrderStatusCommand(private val visitor: User): OrderCommand {
    override fun execute() {
        DataManager.orders.find { it.visitor == visitor && it.status != OrderStatus.COMPLETED && it.status != OrderStatus.CANCELLED}
            ?.let { currentOrder ->
                println("Order is ${currentOrder.status}.")
            } ?: println("You don't have any orders being processed.")
        OutputHelper().pressKeyToContinue()
    }
}