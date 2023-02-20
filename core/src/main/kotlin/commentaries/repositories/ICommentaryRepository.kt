package commentaries.repositories

import commentaries.models.Commentary
import java.util.*

interface ICommentaryRepository {
    fun add(commentary: Commentary)
    fun getById(id: UUID): Commentary
    fun getAllByUserId(id: UUID): List<Commentary>
    fun update(commentary: Commentary)
    fun removeById(id: UUID)
}