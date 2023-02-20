package resumes.models

import users.models.UserDbModel
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"resumes\"")
data class ResumeDbModel(
    @Id
    var id: UUID,
    var name: String,
    var currentJob: String,
    var quote: String,
    var languages: String,
    var frameworks: String,
    var databases: String,
    var otherTechnologies: String,
    var additionalInformation: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserDbModel,
)
