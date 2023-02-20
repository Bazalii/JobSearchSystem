package projects.models

import java.util.*

data class ProjectResponse(
    var id: UUID,
    var name: String,
    var link: String,
    var year: Int,
    var userId: UUID,
)