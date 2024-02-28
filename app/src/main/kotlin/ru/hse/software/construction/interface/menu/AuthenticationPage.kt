package ru.hse.software.construction.`interface`.menu

import ru.hse.software.construction.authentication.AuthenticationManager
import ru.hse.software.construction.authentication.UserFactory
import ru.hse.software.construction.authentication.UserTypes
import ru.hse.software.construction.models.User

class AuthenticationPage {
    fun load(type: UserTypes): User? {
        while (true) {
            println("\nPlease, login or register!\n\t1. Register\n\t2. Login\n\t3. Back")
            print("Choose an option: ")
            when (readlnOrNull()?.toIntOrNull()) {
                1 -> {
                    return UserFactory().createUser(type)
                }
                2 -> {
                    return AuthenticationManager().logIn(type)
                }
                3 -> {
                    return null
                }
                else -> println("Invalid option. Please try again.")
            }
        }
    }
}
