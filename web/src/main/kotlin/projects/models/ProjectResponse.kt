package projects.models

import java.util.*

data class ProjectResponse(
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
    var name: String = "",
    var link: String = "",
    var year: Int = 0,
    var userId: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
)