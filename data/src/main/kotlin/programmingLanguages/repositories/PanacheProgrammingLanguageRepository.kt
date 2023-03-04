package programmingLanguages.repositories

import exceptions.EntityNotFoundException
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import programmingLanguages.models.ProgrammingLanguageDbModel
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class PanacheProgrammingLanguageRepository : PanacheRepositoryBase<ProgrammingLanguageDbModel, UUID> {

    @Transactional
    fun add(programmingLanguageDbModel: ProgrammingLanguageDbModel): ProgrammingLanguageDbModel {
        persist(programmingLanguageDbModel)

        return programmingLanguageDbModel
    }

    fun getAll(): List<ProgrammingLanguageDbModel> {
        return listAll()
    }

    @Transactional
    fun removeById(id: UUID): ProgrammingLanguageDbModel {
        val dbModel = findById(id) ?: throw EntityNotFoundException("Entity not found!")

        deleteById(id)

        return dbModel
    }
}