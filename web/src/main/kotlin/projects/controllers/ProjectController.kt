package projects.controllers

import exceptions.NotEnoughRightsException
import jakarta.annotation.security.RolesAllowed
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
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

@RequestScoped
@Path("projects")
class ProjectController(
    private var _resumeService: IResumeService,
    private var _projectService: IProjectService,
) {

    @Inject
    @Claim(standard = Claims.groups)
    private lateinit var _groups: Set<String>
    private lateinit var _userId: UUID

    @Inject
    private fun init(@Claim(standard = Claims.upn) userIdString: String?) {
        if (userIdString != null) {
            _userId = UUID.fromString(userIdString)
        }
    }

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
    fun getById(@PathParam("id") id: UUID): ProjectResponse {
        val project = _projectService.getById(id)

        return project.toProjectResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("/user/{userId}")
    @RolesAllowed("Admin")
    fun getAllByResumeId(@PathParam("userId") userId: UUID): List<ProjectResponse> {
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

        if (!_groups.contains("Admin") && resume.userId != _userId) {
            throw NotEnoughRightsException("You do not have access to this project!")
        }

        return _projectService.removeById(id).toProjectResponse()
    }
}