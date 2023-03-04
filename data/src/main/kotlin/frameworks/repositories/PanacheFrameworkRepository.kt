package frameworks.repositories

import exceptions.EntityNotFoundException
import frameworks.models.FrameworkDbModel
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PanacheFrameworkRepository : PanacheRepositoryBase<FrameworkDbModel, UUID> {

    fun add(frameworkDbModel: FrameworkDbModel): FrameworkDbModel {
        persist(frameworkDbModel)

        return frameworkDbModel
    }

    fun getAll(): List<FrameworkDbModel> {
        return listAll()
    }

    fun removeById(id: UUID): FrameworkDbModel {
        val dbModel = findById(id) ?: throw EntityNotFoundException("Entity not found!")

        deleteById(id)

        return dbModel
    }
}