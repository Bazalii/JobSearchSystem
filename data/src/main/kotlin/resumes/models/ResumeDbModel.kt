package resumes.models

import databases.models.DatabaseDbModel
import frameworks.models.FrameworkDbModel
import jakarta.persistence.*
import programmingLanguages.models.ProgrammingLanguageDbModel
import projects.models.ProjectDbModel
import users.models.UserDbModel
import workExperience.models.WorkExperienceItemDbModel
import java.util.*

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

    @OneToMany(mappedBy = "resume", cascade = [CascadeType.ALL], orphanRemoval = true)
    var projects: MutableSet<ProjectDbModel> = mutableSetOf(),

    @OneToMany(mappedBy = "resume", cascade = [CascadeType.ALL], orphanRemoval = true)
    var workExperiences: MutableSet<WorkExperienceItemDbModel> = mutableSetOf(),

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserDbModel = UserDbModel(),
)
