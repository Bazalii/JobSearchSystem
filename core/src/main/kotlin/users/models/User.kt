package users.models

import java.util.*

data class User(
    var id: UUID,
    var login: String,
    var email: String,
    var password: String,
    var role: String,
)
