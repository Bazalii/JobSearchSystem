package frameworks.models

import resumes.models.ResumeDbModel
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "\"frameworks\"")
class FrameworkDbModel(
    @Id
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
    var name: String = "",

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "frameworks")
    var resumes: MutableSet<ResumeDbModel> = mutableSetOf(),
)