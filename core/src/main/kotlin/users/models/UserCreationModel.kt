package users.models

import java.util.*

data class UserCreationModel(
    var login: String,
    var password: String,
) {
    fun toUser(): User {
        return User(
            id = UUID.randomUUID(),
            login = login,
            password = password
        )
    }
}