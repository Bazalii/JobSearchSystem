package users.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "\"users\"")
data class UserDbModel(
    @Id
    var id: UUID,
    var login: String,
    var password: String,
)