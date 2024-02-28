package ru.hse.software.construction.admin.commands

import ru.hse.software.construction.parsers.DishParser
import ru.hse.software.construction.repository.DataManager


class AddDishCommand : AdminCommand {
    override fun execute() {
        DataManager.menu.addAll(DishParser().parse())
    }
}