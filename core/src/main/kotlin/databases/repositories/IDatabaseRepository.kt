package databases.repositories

import databases.models.Database
import java.util.*

interface IDatabaseRepository {
    fun add(database: Database): Database
    fun getById(id: UUID): Database
    fun getAll(): List<Database>
    fun removeById(id: UUID): Database
}