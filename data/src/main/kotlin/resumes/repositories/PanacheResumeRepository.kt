package resumes.repositories

import exceptions.EntityNotFoundException
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import resumes.models.ResumeDbModel
import java.util.*

@ApplicationScoped
class PanacheResumeRepository : PanacheRepositoryBase<ResumeDbModel, UUID> {

    fun add(resumeDbModel: ResumeDbModel): ResumeDbModel {
        persist(resumeDbModel)

        return resumeDbModel
    }

    fun getById(id: UUID): ResumeDbModel {
        return findById(id) ?: throw EntityNotFoundException("Entity not found!")
    }

    fun getByUserId(id: UUID): ResumeDbModel {
        return find("user.id", id).firstResult() ?: throw EntityNotFoundException("Entity not found!")
    }

    fun getPage(pageIndex: Int, pageSize: Int): List<ResumeDbModel> {
        return findAll().page(pageIndex, pageSize).list()
    }

    fun countAll(): Long {
        return count()
    }

    fun update(resumeDbModel: ResumeDbModel): ResumeDbModel {
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

        return dbModel
    }

    fun removeById(id: UUID): ResumeDbModel {
        val dbModel = getById(id)

        deleteById(id)

        return dbModel
    }
}