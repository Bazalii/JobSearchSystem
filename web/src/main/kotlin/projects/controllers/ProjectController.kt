package projects.controllers

import exceptions.NotEnoughRightsException
import org.eclipse.microprofile.jwt.Claim
import org.eclipse.microprofile.jwt.Claims
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import projects.extensions.toProjectResponse
import projects.models.ProjectCreationRequest
import projects.models.ProjectResponse
import projects.services.IProjectService
import resumes.services.IResumeService
import java.util.*
import javax.annotation.security.RolesAllowed
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.QueryParam

@RequestScoped
@Path("projects")
class ProjectController(
    private var _resumeService: IResumeService,
    private var _projectService: IProjectService,
) {

    @Inject
    @Claim(standard = Claims.upn)
    private lateinit var userId: String

    @Inject
    @Claim(standard = Claims.groups)
    private lateinit var groups: Set<String>

    @APIResponses(
        APIResponse(responseCode = "200", description = "Project is created"),
        APIResponse(responseCode = "400", description = "Invalid name, link or year"),
        APIResponse(responseCode = "404", description = "Resume with sent id does not exist")
    )
    @POST
    @RolesAllowed("User")
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
    @RolesAllowed("Admin")
    fun getById(id: UUID): ProjectResponse {
        val project = _projectService.getById(id)

        return project.toProjectResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("/user/{userId}")
    @RolesAllowed("Admin")
    fun getAllByResumeId(userId: UUID): List<ProjectResponse> {
        val projects = _projectService.getAllByResumeId(userId)

        return projects.map { it.toProjectResponse() }
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Project with sent id does not exist")
    )
    @DELETE
    @Path("/{id}")
    @RolesAllowed("User", "Admin")
    fun removeById(@PathParam("id") id: UUID): ProjectResponse {
        val project = _projectService.getById(id)

        val resume = _resumeService.getById(project.resumeId)

        if (!groups.contains("Admin") && resume.userId != UUID.fromString(userId)) {
            throw NotEnoughRightsException("You do not have access to this project!")
        }

        return _projectService.removeById(id).toProjectResponse()
    }
}