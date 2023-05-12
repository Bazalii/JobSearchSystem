package commentaries.repositories

import commentaries.models.Commentary
import java.util.*

interface ICommentaryRepository {
    fun add(commentary: Commentary): Commentary
    fun getById(id: UUID): Commentary
    fun getAllByUserId(id: UUID): List<Commentary>
    fun getPage(pageIndex: Int, pageSize: Int): List<Commentary>
    fun countAll(): Long
    fun update(commentary: Commentary): Commentary
    fun removeById(id: UUID): Commentary
}