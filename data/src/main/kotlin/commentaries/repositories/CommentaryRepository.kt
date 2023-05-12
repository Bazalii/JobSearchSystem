package commentaries.repositories

import commentaries.extensions.toCommentary
import commentaries.models.Commentary
import commentaries.models.CommentaryDbModel
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import users.repositories.PanacheUserRepository
import java.util.*

@ApplicationScoped
class CommentaryRepository(
    private var _panacheUserRepository: PanacheUserRepository,
    private var _panacheCommentaryRepository: PanacheCommentaryRepository,
) : ICommentaryRepository {

    @Transactional
    override fun add(commentary: Commentary): Commentary {
        return _panacheCommentaryRepository.add(
            CommentaryDbModel(
                id = commentary.id,
                title = commentary.title,
                body = commentary.body,
                creationTime = commentary.creationTime,
                user = _panacheUserRepository.getById(commentary.userId)
            )
        ).toCommentary()
    }

    override fun getById(id: UUID): Commentary {
        val dbModel = _panacheCommentaryRepository.getById(id)

        return dbModel.toCommentary()
    }

    override fun getAllByUserId(id: UUID): List<Commentary> {
        val dbModels = _panacheCommentaryRepository.getAllByUserId(id)

        return dbModels.map { it.toCommentary() }
    }

    override fun getPage(pageIndex: Int, pageSize: Int): List<Commentary> {
        val dbModels = _panacheCommentaryRepository.getPage(pageIndex, pageSize)

        return dbModels.map { it.toCommentary() }
    }

    override fun countAll(): Long {
        return _panacheCommentaryRepository.countAll()
    }

    @Transactional
    override fun update(commentary: Commentary): Commentary {
        return _panacheCommentaryRepository.update(
            CommentaryDbModel(
                id = commentary.id,
                title = commentary.title,
                body = commentary.body,
                creationTime = commentary.creationTime,
                user = _panacheUserRepository.getById(commentary.userId)
            )
        ).toCommentary()
    }

    @Transactional
    override fun removeById(id: UUID): Commentary {
        return _panacheCommentaryRepository.removeById(id).toCommentary()
    }
}