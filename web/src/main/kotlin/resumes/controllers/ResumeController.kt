package resumes.controllers

import exceptions.NotEnoughRightsException
import jakarta.annotation.security.RolesAllowed
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import org.eclipse.microprofile.jwt.Claim
import org.eclipse.microprofile.jwt.Claims
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import resumes.extensions.toResumeResponse
import resumes.models.ResumeCreationRequest
import resumes.models.ResumeResponse
import resumes.models.ResumeUpdateRequest
import resumes.services.IResumeService
import updatesNotifications.notificators.IUpdatesNotificator
import java.util.*

@RequestScoped
@Path("resumes")
class ResumeController(
    private var _resumeService: IResumeService,
    private val _updatesNotificator: IUpdatesNotificator,
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
        APIResponse(responseCode = "200", description = "Resume is created"),
        APIResponse(responseCode = "400", description = "Invalid resume information"),
        APIResponse(responseCode = "404", description = "User with sent id does not exist")
    )
    @POST
    @RolesAllowed("User")
    fun add(resumeCreationRequest: ResumeCreationRequest): ResumeResponse {
        val creationModel = resumeCreationRequest.toCreationModel(_userId)

        val resumeResponse = _resumeService.add(creationModel).toResumeResponse()

        _updatesNotificator.notifyAll("Resume is added!")

        return resumeResponse
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Resume with sent id does not exist")
    )
    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    fun getById(@PathParam("id") id: UUID): ResumeResponse {
        val resume = _resumeService.getById(id)

        return resume.toResumeResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("/user/{userId}")
    @RolesAllowed("Admin")
    fun getByUserId(@PathParam("userId") userId: UUID): ResumeResponse {
        val resume = _resumeService.getByUserId(userId)

        return resume.toResumeResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Resume with sent id does not exist")
    )
    @PUT
    @Path("/{id}")
    @RolesAllowed("User", "Admin")
    fun update(@PathParam("id") resumeId: UUID, resumeUpdateRequest: ResumeUpdateRequest): ResumeResponse {
        val resumeUpdateModel = resumeUpdateRequest.toResumeUpdateModel(resumeId, _userId)

        if (!_groups.contains("Admin") && resumeUpdateModel.userId != _userId) {
            throw NotEnoughRightsException("You do not have access to this resume!")
        }

        return _resumeService.update(resumeUpdateModel).toResumeResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Resume with sent id does not exist")
    )
    @DELETE
    @Path("/{id}")
    @RolesAllowed("User", "Admin")
    fun removeById(@PathParam("id") id: UUID): ResumeResponse {
        val resume = _resumeService.getById(id)

        if (!_groups.contains("Admin") && resume.userId != _userId) {
            throw NotEnoughRightsException("You do not have access to this resume!")
        }

        val resumeResponse = _resumeService.removeById(id).toResumeResponse()

        _updatesNotificator.notifyAll("Resume is deleted!")

        return resumeResponse
    }
}