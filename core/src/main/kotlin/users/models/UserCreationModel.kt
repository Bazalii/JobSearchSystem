package users.models

import java.util.*

data class UserCreationModel(
    var login: String,
    var email: String,
    var password: String,
    var role: String = "",
) {
    fun toUser(): User {
        return User(
            id = UUID.randomUUID(),
            login = login,
            email = email,
            password = password,
            role = role
        )
    }
}