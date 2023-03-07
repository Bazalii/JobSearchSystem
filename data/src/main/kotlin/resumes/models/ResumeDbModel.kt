package resumes.models

import databases.models.DatabaseDbModel
import frameworks.models.FrameworkDbModel
import programmingLanguages.models.ProgrammingLanguageDbModel
import users.models.UserDbModel
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"resumes\"")
class ResumeDbModel(
    @Id
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
    var name: String = "",
    var currentJob: String = "",
    var quote: String = "",

    @ManyToMany(fetch = FetchType.LAZY)
    var languages: MutableSet<ProgrammingLanguageDbModel> = mutableSetOf(),

    @ManyToMany(fetch = FetchType.LAZY)
    var frameworks: MutableSet<FrameworkDbModel> = mutableSetOf(),

    @ManyToMany(fetch = FetchType.LAZY)
    var databases: MutableSet<DatabaseDbModel> = mutableSetOf(),

    var otherTechnologies: String = "",
    var additionalInformation: String = "",

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserDbModel = UserDbModel(),
)
