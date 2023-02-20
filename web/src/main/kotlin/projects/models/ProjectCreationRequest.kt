package projects.models

import java.util.*

data class ProjectCreationRequest(
    var name: String,
    var link: String,
    var year: Int,
    var userId: UUID,
)