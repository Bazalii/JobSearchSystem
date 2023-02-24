package projects.controllers

import projects.extensions.toProjectResponse
import projects.models.ProjectCreationModel
import projects.models.ProjectCreationRequest
import projects.models.ProjectResponse
import projects.services.IProjectService
import java.util.*
import javax.enterprise.context.RequestScoped
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path

@RequestScoped
@Path("projects")
class ProjectController(
    private var _projectService: IProjectService,
) {

    @POST
    fun add(projectCreationRequest: ProjectCreationRequest) {
        _projectService.add(
            ProjectCreationModel(
                name = projectCreationRequest.name,
                link = projectCreationRequest.link,
                year = projectCreationRequest.year,
                userId = projectCreationRequest.userId
            )
        )
    }

    @GET
    @Path("/{id}")
    fun getById(id: UUID): ProjectResponse {
        val project = _projectService.getById(id)

        return project.toProjectResponse()
    }

    @GET
    @Path("/{userId}")
    fun getAllByUserId(userId: UUID): List<ProjectResponse> {
        val projects = _projectService.getAllByUserId(userId)

        return projects.map { it.toProjectResponse() }
    }

    @DELETE
    @Path("/{id}")
    fun removeById(id: UUID) {
        _projectService.removeById(id)
    }
}