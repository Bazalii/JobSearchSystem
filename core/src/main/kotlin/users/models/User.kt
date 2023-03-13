package users.models

import java.util.*

data class User(
    var id: UUID,
    var login: String,
    var password: String,
)
