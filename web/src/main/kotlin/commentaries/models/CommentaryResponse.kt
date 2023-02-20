package commentaries.models

import java.util.*

data class CommentaryResponse(
    var id: UUID,
    var title: String,
    var body: String,
    var userId: UUID,
)
