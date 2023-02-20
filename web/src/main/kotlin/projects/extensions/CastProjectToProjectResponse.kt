package projects.extensions

import projects.models.Project
import projects.models.ProjectResponse

fun Project.toProjectResponse() = ProjectResponse(
    id,
    name,
    link,
    year,
    userId
)