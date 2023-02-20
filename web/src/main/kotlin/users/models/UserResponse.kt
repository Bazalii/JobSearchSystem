package users.models

import java.util.*

data class UserResponse(
    var id: UUID,
    var login: String,
    var password: String,
)
