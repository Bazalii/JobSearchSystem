package projects.extensions

import projects.models.Project
import projects.models.ProjectDbModel

fun ProjectDbModel.toProject() = Project(
    id,
    name,
    link,
    year,
    resume.id
)