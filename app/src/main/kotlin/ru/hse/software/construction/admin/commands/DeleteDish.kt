package ru.hse.software.construction.admin.commands

import ru.hse.software.construction.parsers.OrderParser
import ru.hse.software.construction.repository.DataManager


class DeleteDishCommand : AdminCommand {
    override fun execute() {
        DataManager.menu.removeAll(OrderParser().parse())
    }
}