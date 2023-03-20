package resumes.controllers

import exceptions.NotEnoughRightsException
import org.eclipse.microprofile.jwt.Claim
import org.eclipse.microprofile.jwt.Claims
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import resumes.extensions.toResumeResponse
import resumes.models.ResumeCreationRequest
import resumes.models.ResumeResponse
import resumes.services.IResumeService
import java.util.*
import javax.annotation.security.RolesAllowed
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path

@RequestScoped
@Path("resumes")
class ResumeController(
    private var _resumeService: IResumeService,
) {

    @Inject
    @Claim(standard = Claims.upn)
    private lateinit var userId: String

    @Inject
    @Claim(standard = Claims.groups)
    private lateinit var groups: Set<String>

    @APIResponses(
        APIResponse(responseCode = "200", description = "Resume is created"),
        APIResponse(responseCode = "400", description = "Invalid resume information"),
        APIResponse(responseCode = "404", description = "User with sent id does not exist")
    )
    @POST
    @RolesAllowed("User")
    fun add(resumeCreationRequest: ResumeCreationRequest): ResumeResponse {
        val creationModel = resumeCreationRequest.toCreationModel(UUID.fromString(userId))

        return _resumeService.add(creationModel).toResumeResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Resume with sent id does not exist")
    )
    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    fun getById(id: UUID): ResumeResponse {
        val resume = _resumeService.getById(id)

        return resume.toResumeResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("/user/{userId}")
    @RolesAllowed("Admin")
    fun getAllByUserId(userId: UUID): List<ResumeResponse> {
        val resumes = _resumeService.getAllByUserId(userId)

        return resumes.map { it.toResumeResponse() }
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Resume with sent id does not exist")
    )
    @DELETE
    @Path("/{id}")
    @RolesAllowed("User", "Admin")
    fun removeById(id: UUID): ResumeResponse {
        val resume = _resumeService.getById(id)

        if (!groups.contains("Admin") && resume.userId != UUID.fromString(userId)) {
            throw NotEnoughRightsException("You do not have access to this resume!")
        }

        return _resumeService.removeById(id).toResumeResponse()
    }
}