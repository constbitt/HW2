package ru.hse.software.construction.`interface`.menu

import ru.hse.software.construction.admin.AdminService
import ru.hse.software.construction.models.User

class AdminPage: UserPage {
    override fun load(user: User) {
        while (true) {
            println("\nWelcome to Admin Page!\n\t1. Add dish to menu\n\t2. Delete dish from menu\n\t" +
                    "3. Statistics\n\t4. See menu\n\t5. Back")
            print("Choose an option: ")
            try {
                when (readlnOrNull()?.toIntOrNull()) {
                    1 -> {
                        AdminService().addDish()
                    }
                    2 -> {
                        AdminService().deleteDish()
                    }
                    3 -> {
                        AdminService().statistics()
                    }
                    4 -> {
                        AdminService().seeMenu()
                    }
                    5 -> {
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