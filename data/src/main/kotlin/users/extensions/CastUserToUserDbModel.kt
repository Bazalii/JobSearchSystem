package users.extensions

import users.models.User
import users.models.UserDbModel

fun User.toUserDbModel() = UserDbModel(
    id,
    login,
    email,
    password,
    role
)