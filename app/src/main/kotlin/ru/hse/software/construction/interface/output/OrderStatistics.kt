package ru.hse.software.construction.`interface`.output

import ru.hse.software.construction.models.Order
import ru.hse.software.construction.repository.DataManager
import java.time.LocalDate
import java.util.*

interface Statistics {
    fun output()
}

class OrderStatistics(private val periodStart: LocalDate, private val periodEnd: LocalDate) : Statistics {

    private fun ordersForPeriod(periodStart: LocalDate, periodEnd: LocalDate): List<Order> {
        return DataManager.orders.filter { it -> it.date in periodStart..periodEnd && it.items.sumOf { it.price } > 0 }
    }
    override fun output() {
        val orders = ordersForPeriod(periodStart, periodEnd)
        println("+------------------------------------------------------------+")
        println("|            Period: $periodStart to $periodEnd                |")
        println("+------------------+--------------------+--------------------+")
        println("|     Order ID     |     Order price    |     Food rating    |")
        println("+------------------+--------------------+--------------------+")
        orders.forEachIndexed { index, order ->
            val orderId = " ${index + 1}"
            val orderPrice = "%.2f".format(Locale.ENGLISH, order.items.sumOf { it.price })
            val foodRating = if (order.reviews.isNotEmpty()) {
                "%.2f".format(Locale.ENGLISH, order.reviews.map { it.grade }.average())
            } else {
                "-"
            }
            println("|   ${orderId.padEnd(15)}|    ${orderPrice.padEnd(15)} |    ${foodRating.padEnd(15)} |")
        }
        println("+------------------+--------------------+--------------------+")
        val totalOrders = orders.size
        val totalOrderPrice = orders.sumOf { it -> it.items.sumOf { it.price } }
        var averageCheck = totalOrderPrice / totalOrders
        if (totalOrders == 0) {
            averageCheck = 0.0
        }
        val averageFoodRating = if (orders.flatMap { it -> it.reviews.map { it.grade } }.isNotEmpty()) {
            "%.2f".format(Locale.ENGLISH, orders.flatMap { it -> it.reviews.map { it.grade } }.average())
        } else {
            "-"
        }
        println("|    Total Orders:        ${totalOrders.toString().padEnd(34)} |")
        println("|    Total Revenue:       ${"%.2f".format(Locale.ENGLISH, totalOrderPrice).padEnd(34)} |")
        println("|    Average Check:       ${"%.2f".format(Locale.ENGLISH, averageCheck).padEnd(34)} |")
        println("|    Average Food Rating: ${averageFoodRating.padEnd(34)} |")
        println("+------------------------------------------------------------+")
        OutputHelper().pressKeyToContinue()
    }
}