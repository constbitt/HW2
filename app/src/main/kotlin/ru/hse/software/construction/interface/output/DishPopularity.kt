package ru.hse.software.construction.`interface`.output

import ru.hse.software.construction.models.Dish
import ru.hse.software.construction.repository.DataManager
import java.util.*

class PopularityRankingByDish : Statistics {
    override fun output() {
        with(popularDishes()) {
            println("+------------------+--------------------+--------------------+")
            println("|   Dish name      |  Number of orders  |         Price      |")
            println("+------------------+--------------------+--------------------+")
            forEach { (dish, count) ->
                println("|  ${dish.name.padEnd(16)}|  ${count.toString().padEnd(18)}|  ${"%.2f ".format(Locale.ENGLISH, dish.price).padEnd(18)}|")
            }
            println("+------------------+--------------------+--------------------+")
        }
        OutputHelper().pressKeyToContinue()
    }

    private fun popularDishes(): Map<Dish, Int> {
        val dishOrderCounts = mutableMapOf<Dish, Int>().withDefault { 0 }
        DataManager.orders.forEach { order ->
            order.items.forEach { dish ->
                dishOrderCounts[dish] = dishOrderCounts.getValue(dish) + 1
            }
        }
        return dishOrderCounts.toList().sortedByDescending { it.second }.toMap()
    }
}
