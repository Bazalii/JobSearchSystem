package workExperience.controllers

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

    @POST
    fun add(workExperienceItemCreationRequest: WorkExperienceItemCreationRequest) {
        _workExperienceService.add(
            WorkExperienceItemCreationModel(
                place = workExperienceItemCreationRequest.place,
                position = workExperienceItemCreationRequest.position,
                startDate = workExperienceItemCreationRequest.startDate,
                endDate = workExperienceItemCreationRequest.endDate,
                userId = workExperienceItemCreationRequest.userId
            )
        )
    }

    @GET
    @Path("/{id}")
    fun getById(id: UUID): WorkExperienceItemResponse {
        val workExperienceItem = _workExperienceService.getById(id)

        return workExperienceItem.toWorkExperienceItemResponse()
    }

    @GET
    @Path("/{userId}")
    fun getAllByUserId(userId: UUID): List<WorkExperienceItemResponse> {
        val workExperienceItems = _workExperienceService.getAllByUserId(userId)

        return workExperienceItems.map { it.toWorkExperienceItemResponse() }
    }

    @DELETE
    @Path("/{id}")
    fun removeById(id: UUID) {
        _workExperienceService.removeById(id)
    }
}