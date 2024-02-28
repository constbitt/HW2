package ru.hse.software.construction.`interface`.menu

import ru.hse.software.construction.models.User
import ru.hse.software.construction.visitor.VisitorService

interface UserPage {
    fun load(user: User)
}

class VisitorPage: UserPage {
    override fun load(user: User) {
        while (true) {
            println("\nWelcome to Visitor Page!\n\t1. Order\n\t2. Add to order\n\t" +
                    "3. Cancel order\n\t4. Pay and review\n\t5. Order status\n\t6. Back")
            print("Choose an option: ")
            try {
                when (readlnOrNull()?.toIntOrNull()) {
                    1 -> {
                        VisitorService(user).createOrder()
                    }
                    2 -> {
                        VisitorService(user).addToOrder()
                    }
                    3 -> {
                        VisitorService(user).cancelOrder()
                    }
                    4 -> {
                        VisitorService(user).payAndReview()
                    }
                    5 -> {
                        VisitorService(user).getStatus()
                    }
                    6 -> {
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