package frameworks.repositories

import frameworks.models.Framework
import java.util.*

interface IFrameworkRepository {
    fun add(framework: Framework): Framework
    fun getAll(): List<Framework>
    fun removeById(id: UUID): Framework
}