package workExperience.controllers

import exceptions.NotEnoughRightsException
import org.eclipse.microprofile.jwt.Claim
import org.eclipse.microprofile.jwt.Claims
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import resumes.services.IResumeService
import workExperience.extensions.toWorkExperienceItemResponse
import workExperience.models.WorkExperienceItemCreationRequest
import workExperience.models.WorkExperienceItemResponse
import workExperience.services.IWorkExperienceService
import java.util.*
import javax.annotation.security.RolesAllowed
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.*

@RequestScoped
@Path("workExperiences")
class WorkExperienceController(
    private var _resumeService: IResumeService,
    private var _workExperienceService: IWorkExperienceService,
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
        APIResponse(responseCode = "200", description = "WorkExperienceItem is created"),
        APIResponse(responseCode = "400", description = "Invalid WorkExperienceItem information"),
        APIResponse(responseCode = "404", description = "Resume with sent id does not exist")
    )
    @POST
    @RolesAllowed("User")
    fun add(workExperienceItemCreationRequest: WorkExperienceItemCreationRequest): WorkExperienceItemResponse {
        val creationModel = workExperienceItemCreationRequest.toCreationModel()

        return _workExperienceService.add(creationModel).toWorkExperienceItemResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "WorkExperienceItem with sent id does not exist")
    )
    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    fun getById(id: UUID): WorkExperienceItemResponse {
        val workExperienceItem = _workExperienceService.getById(id)

        return workExperienceItem.toWorkExperienceItemResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("/user/{userId}")
    @RolesAllowed("Admin")
    fun getAllByResumeId(userId: UUID): List<WorkExperienceItemResponse> {
        val workExperienceItems = _workExperienceService.getAllByResumeId(userId)

        return workExperienceItems.map { it.toWorkExperienceItemResponse() }
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "WorkExperienceItem with sent id does not exist")
    )
    @DELETE
    @Path("/{id}")
    @RolesAllowed("User", "Admin")
    fun removeById(@PathParam("id") id: UUID): WorkExperienceItemResponse {
        val workExperienceItem = _workExperienceService.getById(id)

        val resume = _resumeService.getById(workExperienceItem.resumeId)

        if (!_groups.contains("Admin") && resume.userId != _userId) {
            throw NotEnoughRightsException("You do not have access to this work experience item!")
        }

        return _workExperienceService.removeById(id).toWorkExperienceItemResponse()
    }
}