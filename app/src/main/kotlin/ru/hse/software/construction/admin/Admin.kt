package ru.hse.software.construction.admin

import ru.hse.software.construction.admin.commands.*


interface Admin {
    fun addDish()
    fun deleteDish()
    fun statistics()
    fun seeMenu()
}

class AdminService : Admin {
    override fun addDish() {
        AdminCommandProcessor().processCommand(AddDishCommand())
    }

    override fun deleteDish() {
        AdminCommandProcessor().processCommand(DeleteDishCommand())
    }

    override fun statistics() {
        AdminCommandProcessor().processCommand(StatisticsCommand())
    }

    override fun seeMenu() {
        AdminCommandProcessor().processCommand(SeeMenuCommand())
    }
}