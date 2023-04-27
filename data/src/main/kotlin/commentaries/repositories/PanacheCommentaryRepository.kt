package commentaries.repositories

import commentaries.models.CommentaryDbModel
import exceptions.EntityNotFoundException
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import io.quarkus.panache.common.Sort
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class PanacheCommentaryRepository : PanacheRepositoryBase<CommentaryDbModel, UUID> {

    fun add(commentaryDbModel: CommentaryDbModel): CommentaryDbModel {
        persist(commentaryDbModel)

        return commentaryDbModel
    }

    fun getById(id: UUID): CommentaryDbModel {
        return findById(id) ?: throw EntityNotFoundException("Entity not found!")
    }

    fun getAllByUserId(id: UUID): List<CommentaryDbModel> {
        return list("userId", id)
    }

    fun getPage(pageIndex: Int, pageSize: Int): List<CommentaryDbModel> {
        return findAll(Sort.by("creationTime").descending()).page(pageIndex, pageSize).list()
    }

    fun countAll(): Long {
        return count()
    }

    fun update(commentaryDbModel: CommentaryDbModel): CommentaryDbModel {
        val dbModel = findById(commentaryDbModel.id) ?: throw EntityNotFoundException("Entity not found!")

        dbModel.title = commentaryDbModel.title
        dbModel.body = commentaryDbModel.body

        return dbModel
    }

    fun removeById(id: UUID): CommentaryDbModel {
        val dbModel = getById(id)

        deleteById(id)

        return dbModel
    }
}