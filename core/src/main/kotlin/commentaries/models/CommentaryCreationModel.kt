package commentaries.models

import java.util.*

data class CommentaryCreationModel(
    var title: String,
    var body: String,
    var userId: UUID,
)
