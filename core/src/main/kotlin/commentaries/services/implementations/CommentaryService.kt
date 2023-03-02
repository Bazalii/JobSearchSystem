package commentaries.services.implementations

import commentaries.models.Commentary
import commentaries.models.CommentaryCreationModel
import commentaries.repositories.ICommentaryRepository
import commentaries.services.ICommentaryService
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CommentaryService(
    private var _commentaryRepository: ICommentaryRepository,
) : ICommentaryService {

    override fun add(commentaryCreationModel: CommentaryCreationModel): Commentary {
        return _commentaryRepository.add(
            Commentary(
                id = UUID.randomUUID(),
                title = commentaryCreationModel.title,
                body = commentaryCreationModel.body,
                userId = commentaryCreationModel.userId
            )
        )
    }

    override fun getById(id: UUID): Commentary {
        return _commentaryRepository.getById(id)
    }

    override fun getAllByUserId(id: UUID): List<Commentary> {
        return _commentaryRepository.getAllByUserId(id)
    }

    override fun update(commentary: Commentary): Commentary {
        return _commentaryRepository.update(commentary)
    }

    override fun removeById(id: UUID): Commentary {
        return _commentaryRepository.removeById(id)
    }
}