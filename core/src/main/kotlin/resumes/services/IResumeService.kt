package resumes.services

import resumes.models.Resume
import resumes.models.ResumeCreationModel
import resumes.models.ResumeUpdateModel
import java.util.*

interface IResumeService {
    fun add(resumeCreationModel: ResumeCreationModel): Resume
    fun getById(id: UUID): Resume
    fun getByUserId(id: UUID): Resume
    fun update(resumeUpdateModel: ResumeUpdateModel): Resume
    fun removeById(id: UUID): Resume
}