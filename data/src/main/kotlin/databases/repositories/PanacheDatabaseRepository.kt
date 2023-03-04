package databases.repositories

import databases.models.DatabaseDbModel
import exceptions.EntityNotFoundException
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PanacheDatabaseRepository : PanacheRepositoryBase<DatabaseDbModel, UUID> {

    fun add(databaseDbModel: DatabaseDbModel): DatabaseDbModel {
        persist(databaseDbModel)

        return databaseDbModel
    }

    fun getAll(): List<DatabaseDbModel> {
        return listAll()
    }

    fun removeById(id: UUID): DatabaseDbModel {
        val dbModel = findById(id) ?: throw EntityNotFoundException("Entity not found!")

        deleteById(id)

        return dbModel
    }
}