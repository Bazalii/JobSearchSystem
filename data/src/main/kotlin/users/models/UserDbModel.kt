package users.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

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