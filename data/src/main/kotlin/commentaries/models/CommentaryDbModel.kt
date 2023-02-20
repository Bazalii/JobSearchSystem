package commentaries.models

import users.models.UserDbModel
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"commentaries\"")
data class CommentaryDbModel(
    @Id
    var id: UUID,
    var title: String,
    var body: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserDbModel,
)
