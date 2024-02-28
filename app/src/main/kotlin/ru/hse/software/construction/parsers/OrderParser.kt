package ru.hse.software.construction.parsers

import ru.hse.software.construction.`interface`.output.MenuPage
import ru.hse.software.construction.models.Dish
import ru.hse.software.construction.repository.DataManager

interface Parser<T> {
    fun parse():T
}

class OrderParser : Parser<List<Dish>> {
    override fun parse(): List<Dish> {
        MenuPage().load()
        print("Choose dishes by entering their numbers separated by spaces: ")
        val choices = readlnOrNull()?.split(" ")?.mapNotNull { it.toIntOrNull() } ?: emptyList()

        val selectedDishes = mutableListOf<Dish>()
        for (choice in choices) {
            if (choice in 1..DataManager.menu.size) {
                selectedDishes.add(DataManager.menu[choice - 1])
            }
        }
        if (selectedDishes.isEmpty()) {
            println("Invalid choice.")
        }
        return selectedDishes
    }
}