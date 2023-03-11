package workExperience.controllers

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import workExperience.extensions.toWorkExperienceItemResponse
import workExperience.models.WorkExperienceItemCreationModel
import workExperience.models.WorkExperienceItemCreationRequest
import workExperience.models.WorkExperienceItemResponse
import workExperience.services.IWorkExperienceService
import java.util.*
import javax.enterprise.context.RequestScoped
import javax.ws.rs.*

@RequestScoped
@Path("workExperiences")
class WorkExperienceController(
    private var _workExperienceService: IWorkExperienceService,
) {

    @APIResponses(
        APIResponse(responseCode = "200", description = "WorkExperienceItem is created"),
        APIResponse(responseCode = "400", description = "Invalid WorkExperienceItem information"),
        APIResponse(responseCode = "404", description = "Resume with sent id does not exist")
    )
    @POST
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
    fun getById(id: UUID): WorkExperienceItemResponse {
        val workExperienceItem = _workExperienceService.getById(id)

        return workExperienceItem.toWorkExperienceItemResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("/user/{userId}")
    fun getAllByUserId(userId: UUID): List<WorkExperienceItemResponse> {
        val workExperienceItems = _workExperienceService.getAllByUserId(userId)

        return workExperienceItems.map { it.toWorkExperienceItemResponse() }
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "WorkExperienceItem with sent id does not exist")
    )
    @DELETE
    @Path("/{id}")
    fun removeById(id: UUID): WorkExperienceItemResponse {
        return _workExperienceService.removeById(id).toWorkExperienceItemResponse()
    }
}