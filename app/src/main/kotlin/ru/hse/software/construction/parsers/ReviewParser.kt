package ru.hse.software.construction.parsers

import ru.hse.software.construction.models.Order
import ru.hse.software.construction.models.Review
import java.util.*

class ReviewParser(private val order: Order) : Parser<List<Review>> {
    override fun parse(): List<Review> {
        val reviews = mutableListOf<Review>()
        print("Would you like to review your order? (y/n): ")
        val input = Scanner(System.`in`).nextLine()
        if (input == "y") {
            for (item in order.items) {
                while (true) {
                    print("\tRate the dish '${item.name}' from 1 to 5: ")
                    val gradeInput = readlnOrNull()
                    val grade = gradeInput?.toIntOrNull()
                    if (grade != null && grade in 1..5) {
                        print("\tEnter a brief comment for the dish '${item.name}': ")
                        val comment = readlnOrNull() ?: ""
                        val review = Review(item, grade, comment)
                        reviews.add(review)
                        break
                    } else {
                        println("Invalid grade, please enter a number from 1 to 5.")
                    }
                }
            }
        }
        println("Thank you!")
        return reviews
    }
}