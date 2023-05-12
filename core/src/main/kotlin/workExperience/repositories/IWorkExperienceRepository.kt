package workExperience.repositories

import workExperience.models.WorkExperienceItem
import java.util.*

interface IWorkExperienceRepository {
    fun add(workExperienceItem: WorkExperienceItem): WorkExperienceItem
    fun getById(id: UUID): WorkExperienceItem
    fun getAllByResumeId(id: UUID): List<WorkExperienceItem>
    fun update(workExperienceItem: WorkExperienceItem): WorkExperienceItem
    fun removeById(id: UUID): WorkExperienceItem
}