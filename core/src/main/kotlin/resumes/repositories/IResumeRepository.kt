package resumes.repositories

import resumes.models.Resume
import java.util.*

interface IResumeRepository {
    fun add(resume: Resume)
    fun getById(id: UUID): Resume
    fun getAllByUserId(id: UUID): List<Resume>
    fun update(resume: Resume)
    fun removeById(id: UUID)
}