package projects.models

import java.util.*

data class ProjectCreationModel(
    var name: String,
    var link: String,
    var year: Int,
    var userId: UUID
){
    fun toProject(): Project{
        return Project(
            id = UUID.randomUUID(),
            name = name,
            link = link,
            year = year,
            userId = userId
        )
    }
}