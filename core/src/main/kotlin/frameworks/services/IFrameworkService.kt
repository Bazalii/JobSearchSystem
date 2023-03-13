package frameworks.services

import frameworks.models.Framework
import frameworks.models.FrameworkCreationModel
import java.util.*

interface IFrameworkService {
    fun add(frameworkCreationModel: FrameworkCreationModel): Framework
    fun getById(id: UUID): Framework
    fun getAll(): List<Framework>
    fun removeById(id: UUID): Framework
}