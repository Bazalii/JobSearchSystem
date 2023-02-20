package projects.models

import users.models.UserDbModel
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"projects\"")
data class ProjectDbModel(
    @Id
    var id: UUID,
    var name: String,
    var link: String,
    var year: Int,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserDbModel,
)
