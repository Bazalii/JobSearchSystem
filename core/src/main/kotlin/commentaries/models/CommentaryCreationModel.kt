package commentaries.models

import java.util.*

data class CommentaryCreationModel(
    var title: String,
    var body: String,
    var userId: UUID,
) {
    fun toCommentary(): Commentary {
        return Commentary(
            id = UUID.randomUUID(),
            title = title,
            body = body,
            userId = userId
        )
    }
}
