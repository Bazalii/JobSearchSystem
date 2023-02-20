package workExperience.services

import workExperience.models.WorkExperienceItem
import workExperience.models.WorkExperienceItemCreationModel
import java.util.*

interface IWorkExperienceService {
    fun add(workExperienceItemCreationModel: WorkExperienceItemCreationModel)
    fun getById(id: UUID): WorkExperienceItem
    fun getAllByUserId(id: UUID): List<WorkExperienceItem>
    fun update(workExperienceItem: WorkExperienceItem)
    fun removeById(id: UUID)
}