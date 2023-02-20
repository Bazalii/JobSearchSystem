package resumes.repositories

import exceptions.EntityNotFoundException
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import resumes.models.ResumeDbModel
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PanacheResumeRepository : PanacheRepositoryBase<ResumeDbModel, UUID> {

    fun add(resumeDbModel: ResumeDbModel) {
        persist(resumeDbModel)
    }

    fun getById(id: UUID): ResumeDbModel {
        return findById(id) ?: throw EntityNotFoundException("Entity not found!")
    }

    fun getAllByUserId(id: UUID): List<ResumeDbModel> {
        return list("user_id", id)
    }

    fun update(resumeDbModel: ResumeDbModel) {
        val dbModel = findById(resumeDbModel.id) ?: throw EntityNotFoundException("Entity not found!")

        dbModel.id = resumeDbModel.id
        dbModel.name = resumeDbModel.name
        dbModel.currentJob = resumeDbModel.currentJob
        dbModel.quote = resumeDbModel.quote
        dbModel.languages = resumeDbModel.languages
        dbModel.frameworks = resumeDbModel.frameworks
        dbModel.databases = resumeDbModel.databases
        dbModel.otherTechnologies = resumeDbModel.otherTechnologies
        dbModel.additionalInformation = resumeDbModel.additionalInformation
    }

    fun removeById(id: UUID) {
        val dbModel = getById(id)

        deleteById(id)
    }
}