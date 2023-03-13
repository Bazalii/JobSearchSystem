package users.extensions

import users.models.User
import users.models.UserResponse

fun User.toUserResponse() = UserResponse(
    id,
    login
)