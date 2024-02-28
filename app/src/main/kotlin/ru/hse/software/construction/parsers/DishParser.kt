package ru.hse.software.construction.parsers

import ru.hse.software.construction.models.Dish
import java.util.*

class DishParser : Parser<List<Dish>> {
    override fun parse(): List<Dish> {
        val dishes = mutableListOf<Dish>()
        val scanner = Scanner(System.`in`)
        var input: String
        println("Please input dish info!")
        do {
            print("\tEnter dish name: ")
            val name = scanner.nextLine()

            var price: Double
            while (true) {
                print("\tEnter dish price: ")
                val priceInput = scanner.nextLine()
                try {
                    price = priceInput.toDouble()
                    if (price < 0) {
                        throw NumberFormatException("Price cannot be negative.")
                    }
                    break
                } catch (e: NumberFormatException) {
                    println("Invalid input. Please enter a valid price.")
                }
            }
            var preparationTime: Int
            while (true) {
                print("\tEnter preparation time for the dish: ")
                val timeInput = scanner.nextLine()
                try {
                    preparationTime = timeInput.toInt()
                    if (preparationTime < 0) {
                        throw NumberFormatException("Preparation time cannot be negative.")
                    }
                    break
                } catch (e: NumberFormatException) {
                    println("Invalid input. Please enter a valid preparation time.")
                }
            }
            var quantity: Int
            while (true) {
                print("\tEnter quantity for the dish: ")
                val quantityInput = scanner.nextLine()
                try {
                    quantity = quantityInput.toInt()
                    if (quantity < 0) {
                        throw NumberFormatException("Quantity time cannot be negative.")
                    }
                    break
                } catch (e: NumberFormatException) {
                    println("Invalid input. Please enter a valid quantity.")
                }
            }
            dishes.add(Dish(name, price, preparationTime, quantity))
            println("Dish $name added to the menu.")
            print("Do you want to enter another dish? (y/n): ")
            input = scanner.nextLine()
        } while (input.equals("y", ignoreCase = true))
        return dishes
    }
}
