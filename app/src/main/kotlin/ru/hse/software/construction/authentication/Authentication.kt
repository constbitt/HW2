package ru.hse.software.construction.authentication

import ru.hse.software.construction.models.User
import ru.hse.software.construction.parsers.UserInfoParser
import ru.hse.software.construction.repository.DataManager

interface Authentication {
    fun register(): User?
    fun logIn(type: UserTypes): User?
}

class AuthenticationManager: Authentication {
    override fun register(): User? {
        println("Please, register!")
        return UserInfoParser().parse().let { user ->
            val userAlreadyRegistered = DataManager.visitors.contains(user) || DataManager.admins.contains(user)
            if (userAlreadyRegistered) {
                println("This user is already registered.")
                null
            } else {
                println("Sign up successful.")
                user
            }
        } ?: run {
            println("Sign up unsuccessful.")
            null
        }
    }

    override fun logIn(type: UserTypes): User? {
        println("Please, log in!")
        val user = UserInfoParser().parse()
        val loggedInUser = when (type) {
            UserTypes.ADMIN -> DataManager.admins.firstOrNull { it == user }
            UserTypes.VISITOR -> DataManager.visitors.firstOrNull { it == user }
        }
        return if (loggedInUser != null) {
            println("Login successful. Welcome, ${loggedInUser.username}!")
            loggedInUser
        } else {
            println("Login unsuccessful.")
            null
        }
    }
}
