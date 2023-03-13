package commentaries.services.implementations

import commentaries.models.Commentary
import commentaries.models.CommentaryCreationModel
import commentaries.repositories.ICommentaryRepository
import commentaries.services.ICommentaryService
import commonClasses.IThrowingValidator
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CommentaryService(
    private var _commentaryRepository: ICommentaryRepository,
    private var _commentaryValidator: IThrowingValidator<Commentary>,
) : ICommentaryService {

    override fun add(commentaryCreationModel: CommentaryCreationModel): Commentary {
        val commentary = commentaryCreationModel.toCommentary()

        _commentaryValidator.validate(commentary)

        return _commentaryRepository.add(commentary)
    }

    override fun getById(id: UUID): Commentary {
        return _commentaryRepository.getById(id)
    }

    override fun getAllByUserId(id: UUID): List<Commentary> {
        return _commentaryRepository.getAllByUserId(id)
    }

    override fun update(commentary: Commentary): Commentary {
        _commentaryValidator.validate(commentary)

        return _commentaryRepository.update(commentary)
    }

    override fun removeById(id: UUID): Commentary {
        return _commentaryRepository.removeById(id)
    }
}