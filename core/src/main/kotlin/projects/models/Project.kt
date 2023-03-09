package projects.models

import java.util.*

data class Project(
    var id: UUID,
    var name: String,
    var link: String,
    var year: Int,
    var resumeId: UUID
)
