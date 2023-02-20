package projects.repositories

import projects.models.Project
import java.util.*

interface IProjectRepository {
    fun add(project: Project)
    fun getById(id: UUID): Project
    fun getAllByUserId(id: UUID): List<Project>
    fun update(project: Project)
    fun removeById(id: UUID)
}