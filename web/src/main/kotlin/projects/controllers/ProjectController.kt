package projects.controllers

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
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

    @APIResponses(
        APIResponse(responseCode = "200", description = "Project is created"),
        APIResponse(responseCode = "400", description = "Invalid name, link or year")
    )
    @POST
    fun add(projectCreationRequest: ProjectCreationRequest): ProjectResponse {
        val creationModel = projectCreationRequest.toCreationModel()

        return _projectService.add(creationModel).toProjectResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Project with sent id does not exist")
    )
    @GET
    @Path("/{id}")
    fun getById(id: UUID): ProjectResponse {
        val project = _projectService.getById(id)

        return project.toProjectResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("/{userId}")
    fun getAllByUserId(userId: UUID): List<ProjectResponse> {
        val projects = _projectService.getAllByUserId(userId)

        return projects.map { it.toProjectResponse() }
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Project with sent id does not exist")
    )
    @DELETE
    @Path("/{id}")
    fun removeById(id: UUID): ProjectResponse {
        return _projectService.removeById(id).toProjectResponse()
    }
}