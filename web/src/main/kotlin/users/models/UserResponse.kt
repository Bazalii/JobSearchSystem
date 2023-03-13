package users.models

import java.util.*

data class UserResponse(
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
    var login: String = "",
)
