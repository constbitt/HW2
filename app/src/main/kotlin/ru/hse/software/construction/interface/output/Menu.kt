package ru.hse.software.construction.`interface`.output

import ru.hse.software.construction.`interface`.menu.Page
import ru.hse.software.construction.repository.DataManager

class MenuPage: Page {
    override fun load() {
        println("╔══════════════════════════════════╗")
        println("║             MENU                 ║")
        println("╠══════════════════════════════════╣")
        DataManager.menu.forEachIndexed { index, dish ->
            println("║ ${index + 1}. ${dish.name.padEnd(18)} ${dish.price.toString().padStart(10)} ║")
        }
        println("╚══════════════════════════════════╝")
    }
}