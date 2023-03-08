package databases.services

import databases.models.Database
import databases.models.DatabaseCreationModel
import java.util.*

interface IDatabaseService {
    fun add(databaseCreationModel: DatabaseCreationModel): Database
    fun getById(id: UUID): Database
    fun getAll(): List<Database>
    fun removeById(id: UUID): Database
}