package commentaries.models

import users.models.UserDbModel
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"commentaries\"")
data class CommentaryDbModel(
    @Id
    var id: UUID,
    var title: String,
    var body: String,

    @Column(name = "creation_time")
    var creationTime: LocalDateTime = LocalDateTime.MIN,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserDbModel,
)
