package ru.hse.software.construction.authentication

import ru.hse.software.construction.models.User
import ru.hse.software.construction.repository.DataManager

enum class UserTypes {
    ADMIN, VISITOR
}

interface NewUser {
    fun add(user: User)
}

class NewAdmin: NewUser {
    override fun add(user: User) {
        DataManager.admins.add(user)
    }
}

class NewVisitor: NewUser {
    override fun add(user: User) {
        DataManager.visitors.add(user)
    }
}

class UserFactory {
    fun createUser(type: UserTypes): User? {
        return AuthenticationManager().register()?.also { user ->
            when (type) {
                UserTypes.ADMIN -> NewAdmin().add(user)
                UserTypes.VISITOR -> NewVisitor().add(user)
            }
        }
    }
}
