package users.models

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "\"users\"")
data class UserDbModel(
    @Id
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
    var login: String = "",
    var email: String = "",
    var password: String = "",
    var role: String = "",
)