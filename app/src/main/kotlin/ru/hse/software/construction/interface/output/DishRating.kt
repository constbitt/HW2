package ru.hse.software.construction.`interface`.output

import ru.hse.software.construction.models.Dish
import ru.hse.software.construction.repository.DataManager
import java.util.*

class AverageRatingByDish : Statistics {
    override fun output() {
        averageRatingByDish().also { dishRatings ->
            println("+------------------+--------------------+--------------------+")
            println("|     Dish name    |   Average rating   |        Price       |")
            println("+------------------+--------------------+--------------------+")

            dishRatings.forEach { (dish, averageRating) ->
                val ratingText = averageRating?.let { "%.2f".format(Locale.ENGLISH, it) } ?: "-"
                println("|  ${dish.name.padEnd(16)}|  ${ratingText.padEnd(17)} |  ${"%.2f ".format(Locale.ENGLISH, dish.price).padEnd(17)} |")
            }

            println("+------------------+--------------------+--------------------+")
        }
        OutputHelper().pressKeyToContinue()
    }

    private fun averageRatingByDish(): Map<Dish, Double?> {
        val dishRatings = DataManager.menu.associateWith { mutableListOf<Int>() }
        DataManager.orders.flatMap { it.reviews }.forEach { review ->
            dishRatings[review.dish]?.add(review.grade)
        }
        return dishRatings.mapValues { (_, grades) ->
            grades.takeIf { it.isNotEmpty() }?.average()
        }.toList().sortedByDescending { it.second }.toMap()
    }
}
