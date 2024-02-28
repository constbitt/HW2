package ru.hse.software.construction.parsers

import ru.hse.software.construction.models.User

class UserInfoParser: Parser<User> {
    override fun parse(): User {
        print("\tEnter username: ")
        val username = readln()
        print("\tEnter password: ")
        val password = readln()
        return User(username, password)
    }

}