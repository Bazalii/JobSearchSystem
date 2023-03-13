package resumes.controllers

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import resumes.extensions.toResumeResponse
import resumes.models.ResumeCreationRequest
import resumes.models.ResumeResponse
import resumes.services.IResumeService
import java.util.*
import javax.enterprise.context.RequestScoped
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path

@RequestScoped
@Path("resumes")
class ResumeController(
    private var _resumeService: IResumeService,
) {

    @APIResponses(
        APIResponse(responseCode = "200", description = "Resume is created"),
        APIResponse(responseCode = "400", description = "Invalid resume information"),
        APIResponse(responseCode = "404", description = "User with sent id does not exist")
    )
    @POST
    fun add(resumeCreationRequest: ResumeCreationRequest): ResumeResponse {
        val creationModel = resumeCreationRequest.toCreationModel()

        return _resumeService.add(creationModel).toResumeResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Resume with sent id does not exist")
    )
    @GET
    @Path("/{id}")
    fun getById(id: UUID): ResumeResponse {
        val resume = _resumeService.getById(id)

        return resume.toResumeResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("/user/{userId}")
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
    fun removeById(id: UUID): ResumeResponse {
        return _resumeService.removeById(id).toResumeResponse()
    }
}