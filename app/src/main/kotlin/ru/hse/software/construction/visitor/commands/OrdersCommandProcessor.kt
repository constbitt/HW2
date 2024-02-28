package ru.hse.software.construction.visitor.commands

class OrdersCommandProcessor {
    fun processCommand(orderCommand: OrderCommand): OrdersCommandProcessor = apply {
        orderCommand.execute()
    }
}