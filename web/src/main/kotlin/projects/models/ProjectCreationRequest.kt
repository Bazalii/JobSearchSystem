package projects.models

import java.util.*

data class ProjectCreationRequest(
    var name: String = "",
    var link: String = "",
    var year: Int = 0,
    var userId: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
)