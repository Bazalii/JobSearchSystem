package projects.services

import projects.models.Project
import projects.models.ProjectCreationModel
import java.util.*

interface IProjectService {
    fun add(projectCreationModel: ProjectCreationModel): Project
    fun getById(id: UUID): Project
    fun getAllByUserId(id: UUID): List<Project>
    fun update(project: Project): Project
    fun removeById(id: UUID): Project
}