package resumes.repositories

import resumes.models.Resume
import java.util.*

interface IResumeRepository {
    fun add(resume: Resume): Resume
    fun getById(id: UUID): Resume
    fun getByUserId(id: UUID): Resume
    fun update(resume: Resume): Resume
    fun removeById(id: UUID): Resume
}