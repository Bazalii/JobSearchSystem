package databases.services.implementations

import commonClasses.IThrowingValidator
import databases.models.Database
import databases.models.DatabaseCreationModel
import databases.repositories.IDatabaseRepository
import databases.services.IDatabaseService
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DatabaseService(
    private var _databaseRepository: IDatabaseRepository,
    private var _databaseValidator: IThrowingValidator<Database>,
) : IDatabaseService {
    override fun add(databaseCreationModel: DatabaseCreationModel): Database {
        val database = databaseCreationModel.toDatabase()

        _databaseValidator.validate(database)

        return _databaseRepository.add(database)
    }

    override fun getById(id: UUID): Database {
        return _databaseRepository.getById(id)
    }

    override fun getAll(): List<Database> {
        return _databaseRepository.getAll()
    }

    override fun removeById(id: UUID): Database {
        return _databaseRepository.removeById(id)
    }
}
