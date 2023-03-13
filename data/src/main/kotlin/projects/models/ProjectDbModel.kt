package projects.models

import resumes.models.ResumeDbModel
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
    @JoinColumn(name = "resume_id")
    var resume: ResumeDbModel,
)
