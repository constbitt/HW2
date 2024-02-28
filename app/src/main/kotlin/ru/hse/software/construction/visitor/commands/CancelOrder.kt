package ru.hse.software.construction.visitor.commands

import ru.hse.software.construction.`interface`.output.OutputHelper
import ru.hse.software.construction.models.OrderStatus
import ru.hse.software.construction.models.User
import ru.hse.software.construction.repository.DataManager

class CancelOrderCommand(private val visitor: User): OrderCommand {
    override fun execute() {
        DataManager.orders.find { it.visitor == visitor && it.status != OrderStatus.READY && it.status != OrderStatus.CANCELLED && it.status != OrderStatus.COMPLETED }
            ?.let { currentOrder ->
                currentOrder.status = OrderStatus.CANCELLED
                println("Order cancelled successfully.")
            } ?: println("You can't cancel your order is completed or doesn't exist.")
        OutputHelper().pressKeyToContinue()
    }
}