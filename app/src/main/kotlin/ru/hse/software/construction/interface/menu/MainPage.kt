package ru.hse.software.construction.`interface`.menu

import ru.hse.software.construction.authentication.UserTypes
import ru.hse.software.construction.`interface`.output.OrdersPage
import ru.hse.software.construction.`interface`.output.OutputHelper
import ru.hse.software.construction.kitchen.KitchenService
import ru.hse.software.construction.parsers.FolderParser
import ru.hse.software.construction.repository.DataManager

interface Page {
    fun load()
}

class MainPage: Page {
    private var defaultLocation: String = "files"
    override fun load() {
        defaultLocation = FolderParser(defaultLocation).parse()
        if (defaultLocation == "") {
            return
        }
        KitchenService
        DataManager.loadEntities(defaultLocation)
        OutputHelper().pressKeyToContinue()
        while (true) {
            println("\nWelcome to the Main Page!\n\t1. Visitor\n\t2. Admin\n\t" +
                    "3. Order information board\n\t4. Exit")
            print("Choose an option: ")
            try {
                when (readlnOrNull()?.toIntOrNull()) {
                    1 -> {
                        val user = AuthenticationPage().load(UserTypes.VISITOR)
                        if (user != null) {
                            VisitorPage().load(user)
                        }
                    }
                    2 -> {
                        val user = AuthenticationPage().load(UserTypes.ADMIN)
                        if (user != null) {
                            AdminPage().load(user)
                        }
                    }
                    3 -> {
                        OrdersPage().load()
                    }
                    4 -> {
                        println("Exiting...")
                        DataManager.saveEntities(defaultLocation)
                        return
                    }
                    else -> println("Invalid option. Please try again.")
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
