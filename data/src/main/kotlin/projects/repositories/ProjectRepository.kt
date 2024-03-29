package projects.repositories

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import projects.extensions.toProject
import projects.models.Project
import projects.models.ProjectDbModel
import resumes.repositories.PanacheResumeRepository
import java.util.*

@ApplicationScoped
class ProjectRepository(
    private var _panacheResumeRepository: PanacheResumeRepository,
    private var _panacheProjectRepository: PanacheProjectRepository,
) : IProjectRepository {

    @Transactional
    override fun add(project: Project): Project {
        return _panacheProjectRepository.add(
            ProjectDbModel(
                id = project.id,
                name = project.name,
                link = project.link,
                year = project.year,
                resume = _panacheResumeRepository.getById(project.resumeId)
            )
        ).toProject()
    }

    override fun getById(id: UUID): Project {
        val dbModel = _panacheProjectRepository.getById(id)

        return dbModel.toProject()
    }

    override fun getAllByResumeId(id: UUID): List<Project> {
        val dbModels = _panacheProjectRepository.getAllByResumeId(id)

        return dbModels.map { it.toProject() }
    }

    @Transactional
    override fun update(project: Project): Project {
        return _panacheProjectRepository.update(
            ProjectDbModel(
                id = project.id,
                name = project.name,
                link = project.link,
                year = project.year,
                resume = _panacheResumeRepository.getById(project.resumeId)
            )
        ).toProject()
    }

    @Transactional
    override fun removeById(id: UUID): Project {
        return _panacheProjectRepository.removeById(id).toProject()
    }
}