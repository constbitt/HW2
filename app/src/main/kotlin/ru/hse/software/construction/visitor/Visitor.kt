package ru.hse.software.construction.visitor

import ru.hse.software.construction.models.User
import ru.hse.software.construction.visitor.commands.*

interface Visitor {
    fun createOrder()
    fun addToOrder()
    fun cancelOrder()
    fun payAndReview()
    fun getStatus()
}
class VisitorService(private val visitor: User): Visitor {
    override fun createOrder() {
        OrdersCommandProcessor().processCommand(CreateOrderCommand(visitor))
        OrdersCommandProcessor().processCommand(AddToOrderCommand(visitor))
    }

    override fun addToOrder() {
        OrdersCommandProcessor().processCommand(AddToOrderCommand(visitor))
    }

    override fun cancelOrder() {
        OrdersCommandProcessor().processCommand(CancelOrderCommand(visitor))
    }

    override fun payAndReview() {
        OrdersCommandProcessor().processCommand(PayOrderCommand(visitor))
    }

    override fun getStatus() {
        OrdersCommandProcessor().processCommand(GetOrderStatusCommand(visitor))
    }
}