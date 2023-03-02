package projects.services.implementations

import projects.models.Project
import projects.models.ProjectCreationModel
import projects.repositories.IProjectRepository
import projects.services.IProjectService
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProjectService(
    private var _projectRepository: IProjectRepository
) : IProjectService {

    override fun add(projectCreationModel: ProjectCreationModel): Project {
        return _projectRepository.add(
            Project(
                id = UUID.randomUUID(),
                name = projectCreationModel.name,
                link = projectCreationModel.link,
                year = projectCreationModel.year,
                userId = projectCreationModel.userId
            )
        )
    }

    override fun getById(id: UUID): Project {
        return _projectRepository.getById(id)
    }

    override fun getAllByUserId(id: UUID): List<Project> {
        return _projectRepository.getAllByUserId(id)
    }

    override fun update(project: Project): Project {
        return _projectRepository.update(project)
    }

    override fun removeById(id: UUID): Project {
        return _projectRepository.removeById(id)
    }
}