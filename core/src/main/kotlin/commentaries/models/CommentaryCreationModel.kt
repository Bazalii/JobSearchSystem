package commentaries.models

import java.time.LocalDateTime
import java.time.ZoneOffset
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
            creationTime = LocalDateTime.now(ZoneOffset.UTC),
            userId = userId
        )
    }
}
