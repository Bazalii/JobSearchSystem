package resumes.controllers

import resumes.extensions.toResumeResponse
import resumes.models.ResumeCreationModel
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

    @POST
    fun add(resumeCreationRequest: ResumeCreationRequest) {
        _resumeService.add(
            ResumeCreationModel(
                name = resumeCreationRequest.name,
                currentJob = resumeCreationRequest.currentJob,
                quote = resumeCreationRequest.quote,
                languages = resumeCreationRequest.languages,
                frameworks = resumeCreationRequest.frameworks,
                databases = resumeCreationRequest.databases,
                otherTechnologies = resumeCreationRequest.otherTechnologies,
                additionalInformation = resumeCreationRequest.additionalInformation,
                userId = resumeCreationRequest.userId
            )
        )
    }

    @GET
    @Path("/{id}")
    fun getById(id: UUID): ResumeResponse {
        val resume = _resumeService.getById(id)

        return resume.toResumeResponse()
    }

    @GET
    @Path("/{userId}")
    fun getAllByUserId(userId: UUID): List<ResumeResponse> {
        val resumes = _resumeService.getAllByUserId(userId)

        return resumes.map { it.toResumeResponse() }
    }

    @DELETE
    @Path("/{id}")
    fun removeById(id: UUID) {
        _resumeService.removeById(id)
    }
}