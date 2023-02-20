package resumes.services

import resumes.models.Resume
import resumes.models.ResumeCreationModel
import java.util.*

interface IResumeService {
    fun add(resumeCreationModel: ResumeCreationModel)
    fun getById(id: UUID): Resume
    fun getAllByUserId(id: UUID): List<Resume>
    fun update(resume: Resume)
    fun removeById(id: UUID)
}