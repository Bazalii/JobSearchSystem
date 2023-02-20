package workExperience.repositories

import workExperience.models.WorkExperienceItem
import java.util.*

interface IWorkExperienceRepository {
    fun add(workExperienceItem: WorkExperienceItem)
    fun getById(id: UUID): WorkExperienceItem
    fun getAllByUserId(id: UUID): List<WorkExperienceItem>
    fun update(workExperienceItem: WorkExperienceItem)
    fun removeById(id: UUID)
}