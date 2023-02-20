package commentaries.models

import java.util.*

data class CommentaryCreationRequest(
    var author: String,
    var title: String,
    var body: String,
    var userId: UUID,
)
