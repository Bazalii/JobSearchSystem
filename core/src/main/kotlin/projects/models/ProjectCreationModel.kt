package projects.models

import java.util.*

data class ProjectCreationModel(
    var name: String,
    var link: String,
    var year: Int,
    var resumeId: UUID,
) {
    fun toProject(): Project {
        return Project(
            id = UUID.randomUUID(),
            name = name,
            link = link,
            year = year,
            resumeId = resumeId
        )
    }
}