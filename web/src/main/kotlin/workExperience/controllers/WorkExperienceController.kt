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
@Path("workExperience")
class WorkExperienceController(
    private var _workExperienceService: IWorkExperienceService,
) {

    @POST
    fun add(workExperienceItemCreationRequest: WorkExperienceItemCreationRequest) {
        _workExperienceService.add(
            WorkExperienceItemCreationModel(
                place = workExperienceItemCreationRequest.place,
                position = workExperienceItemCreationRequest.position,
                dates = workExperienceItemCreationRequest.dates,
                userId = workExperienceItemCreationRequest.userId
            )
        )
    }

    @GET
    @Path("getById")
    fun getById(id: UUID): WorkExperienceItemResponse {
        val workExperienceItem = _workExperienceService.getById(id)

        return workExperienceItem.toWorkExperienceItemResponse()
    }

    @GET
    @Path("getAllByUserId")
    fun getAllByUserId(id: UUID): List<WorkExperienceItemResponse> {
        val workExperienceItems = _workExperienceService.getAllByUserId(id)

        return workExperienceItems.map { it.toWorkExperienceItemResponse() }
    }

    @DELETE
    fun removeById(id: UUID) {
        _workExperienceService.removeById(id)
    }
}