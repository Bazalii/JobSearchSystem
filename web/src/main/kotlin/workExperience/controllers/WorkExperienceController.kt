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
        APIResponse(responseCode = "400", description = "Invalid WorkExperienceItem information")
    )
    @POST
    fun add(workExperienceItemCreationRequest: WorkExperienceItemCreationRequest): WorkExperienceItemResponse {
        return _workExperienceService.add(
            WorkExperienceItemCreationModel(
                place = workExperienceItemCreationRequest.place,
                position = workExperienceItemCreationRequest.position,
                startDate = workExperienceItemCreationRequest.startDate,
                endDate = workExperienceItemCreationRequest.endDate,
                userId = workExperienceItemCreationRequest.userId
            )
        ).toWorkExperienceItemResponse()
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
    @Path("/{userId}")
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