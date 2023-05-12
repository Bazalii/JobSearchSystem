package projects.services.implementations

import commonClasses.IThrowingValidator
import jakarta.enterprise.context.ApplicationScoped
import projects.models.Project
import projects.models.ProjectCreationModel
import projects.repositories.IProjectRepository
import projects.services.IProjectService
import java.util.*

@ApplicationScoped
class ProjectService(
    private var _projectRepository: IProjectRepository,
    private var _projectValidator: IThrowingValidator<Project>,
) : IProjectService {

    override fun add(projectCreationModel: ProjectCreationModel): Project {
        val project = projectCreationModel.toProject()

        _projectValidator.validate(project)

        return _projectRepository.add(project)
    }

    override fun getById(id: UUID): Project {
        return _projectRepository.getById(id)
    }

    override fun getAllByResumeId(id: UUID): List<Project> {
        return _projectRepository.getAllByResumeId(id)
    }

    override fun update(project: Project): Project {
        _projectValidator.validate(project)

        return _projectRepository.update(project)
    }

    override fun removeById(id: UUID): Project {
        return _projectRepository.removeById(id)
    }
}