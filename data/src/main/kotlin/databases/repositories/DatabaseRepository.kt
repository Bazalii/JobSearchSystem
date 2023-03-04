package databases.repositories

import databases.extensions.toDatabase
import databases.models.Database
import databases.models.DatabaseDbModel
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class DatabaseRepository(
    private var _panacheDatabaseRepository: PanacheDatabaseRepository,
) : IDatabaseRepository {

    @Transactional
    override fun add(database: Database): Database {
        return _panacheDatabaseRepository.add(
            DatabaseDbModel(
                id = database.id,
                name = database.name
            )
        ).toDatabase()
    }

    override fun getAll(): List<Database> {
        return _panacheDatabaseRepository.getAll().map { it.toDatabase() }
    }

    @Transactional
    override fun removeById(id: UUID): Database {
        return _panacheDatabaseRepository.removeById(id).toDatabase()
    }
}