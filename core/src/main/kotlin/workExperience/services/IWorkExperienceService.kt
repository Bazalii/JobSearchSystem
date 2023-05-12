package workExperience.services

import workExperience.models.WorkExperienceItem
import workExperience.models.WorkExperienceItemCreationModel
import java.util.*

interface IWorkExperienceService {
    fun add(workExperienceItemCreationModel: WorkExperienceItemCreationModel): WorkExperienceItem
    fun getById(id: UUID): WorkExperienceItem
    fun getAllByResumeId(id: UUID): List<WorkExperienceItem>
    fun update(workExperienceItem: WorkExperienceItem): WorkExperienceItem
    fun removeById(id: UUID): WorkExperienceItem
}