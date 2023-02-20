package workExperience.models

import users.models.UserDbModel
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"workExperienceItems\"")
data class WorkExperienceItemDbModel(
    @Id
    var id: UUID,
    var place: String,
    var position: String,
    var dates: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserDbModel,
)
