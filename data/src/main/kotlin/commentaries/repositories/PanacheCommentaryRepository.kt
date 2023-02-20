package commentaries.repositories

import commentaries.models.CommentaryDbModel
import exceptions.EntityNotFoundException
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PanacheCommentaryRepository : PanacheRepositoryBase<CommentaryDbModel, UUID> {

    fun add(commentaryDbModel: CommentaryDbModel) {
        persist(commentaryDbModel)
    }

    fun getById(id: UUID): CommentaryDbModel {
        return findById(id) ?: throw EntityNotFoundException("Entity not found!")
    }

    fun getAllByUserId(id: UUID): List<CommentaryDbModel> {
        return list("user_id", id)
    }

    fun update(commentaryDbModel: CommentaryDbModel) {
        val dbModel = findById(commentaryDbModel.id) ?: throw EntityNotFoundException("Entity not found!")

        dbModel.title = commentaryDbModel.title
        dbModel.body = commentaryDbModel.body
    }

    fun removeById(id: UUID) {
        val dbModel = getById(id)

        deleteById(id)
    }
}