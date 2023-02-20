package commentaries.repositories

import commentaries.extensions.toCommentary
import commentaries.models.Commentary
import commentaries.models.CommentaryDbModel
import users.repositories.PanacheUserRepository
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class CommentaryRepository(
    private var _panacheUserRepository: PanacheUserRepository,
    private var _panacheCommentaryRepository: PanacheCommentaryRepository,
) : ICommentaryRepository {

    @Transactional
    override fun add(commentary: Commentary) {
        _panacheCommentaryRepository.add(
            CommentaryDbModel(
                id = commentary.id,
                title = commentary.title,
                body = commentary.body,
                user = _panacheUserRepository.getById(commentary.userId)
            )
        )
    }

    override fun getById(id: UUID): Commentary {
        val dbModel = _panacheCommentaryRepository.getById(id)

        return dbModel.toCommentary()
    }

    override fun getAllByUserId(id: UUID): List<Commentary> {
        val dbModels = _panacheCommentaryRepository.getAllByUserId(id)

        return dbModels.map { it.toCommentary() }
    }

    @Transactional
    override fun update(commentary: Commentary) {
        _panacheCommentaryRepository.update(
            CommentaryDbModel(
                id = commentary.id,
                title = commentary.title,
                body = commentary.body,
                user = _panacheUserRepository.getById(commentary.userId)
            )
        )
    }

    @Transactional
    override fun removeById(id: UUID) {
        _panacheCommentaryRepository.removeById(id)
    }
}