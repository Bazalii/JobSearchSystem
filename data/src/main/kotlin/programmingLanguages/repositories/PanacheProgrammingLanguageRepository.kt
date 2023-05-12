package programmingLanguages.repositories

import exceptions.EntityNotFoundException
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import programmingLanguages.models.ProgrammingLanguageDbModel
import java.util.*

@ApplicationScoped
class PanacheProgrammingLanguageRepository : PanacheRepositoryBase<ProgrammingLanguageDbModel, UUID> {

    @Transactional
    fun add(programmingLanguageDbModel: ProgrammingLanguageDbModel): ProgrammingLanguageDbModel {
        persist(programmingLanguageDbModel)

        return programmingLanguageDbModel
    }

    fun getById(id: UUID): ProgrammingLanguageDbModel {
        return findById(id) ?: throw EntityNotFoundException("Entity not found!")
    }

    fun getAll(): List<ProgrammingLanguageDbModel> {
        return listAll()
    }

    @Transactional
    fun removeById(id: UUID): ProgrammingLanguageDbModel {
        val dbModel = findById(id) ?: throw EntityNotFoundException("Entity not found!")

        dbModel.resumes.forEach { resumeDbModel -> resumeDbModel.languages.remove(dbModel) }

        deleteById(id)

        return dbModel
    }
}