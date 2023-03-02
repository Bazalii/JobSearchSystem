package workExperience.models

import users.models.UserDbModel
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"workExperienceItems\"")
data class WorkExperienceItemDbModel(
    @Id
    var id: UUID,
    var place: String,
    var position: String,
    var startDate: LocalDate,
    var endDate: LocalDate,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: UserDbModel,
)
