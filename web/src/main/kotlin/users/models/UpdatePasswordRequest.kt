package users.models

import java.util.*

data class UpdatePasswordRequest(
    var id: UUID,
    var password: String
)