package projects.repositories

import projects.models.Project
import java.util.*

interface IProjectRepository {
    fun add(project: Project): Project
    fun getById(id: UUID): Project
    fun getAllByResumeId(id: UUID): List<Project>
    fun update(project: Project): Project
    fun removeById(id: UUID): Project
}