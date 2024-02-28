package ru.hse.software.construction.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.time.LocalDate

@Serializable
enum class OrderStatus { CREATED, ACCEPTED, IN_PROGRESS, READY, COMPLETED, CANCELLED }

@Serializable
data class Order(val visitor: User) {
    var status: OrderStatus = OrderStatus.CREATED
    val items = mutableListOf<Dish>()
    var reviews = listOf<Review>()
    @Transient
    val date: LocalDate = LocalDate.now()
}