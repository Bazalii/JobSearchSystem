package workExperience.models

import jakarta.persistence.*
import resumes.models.ResumeDbModel
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "\"workExperienceItems\"")
class WorkExperienceItemDbModel(
    @Id
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
    var place: String = "",
    var position: String = "",
    var startDate: LocalDate = LocalDate.MIN,
    var endDate: LocalDate = LocalDate.MAX,

    @ManyToOne
    @JoinColumn(name = "resume_id")
    var resume: ResumeDbModel = ResumeDbModel(),
)
