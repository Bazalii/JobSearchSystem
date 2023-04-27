package projects.models

import jakarta.persistence.*
import resumes.models.ResumeDbModel
import java.util.*

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
