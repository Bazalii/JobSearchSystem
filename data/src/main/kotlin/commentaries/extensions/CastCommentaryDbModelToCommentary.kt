package commentaries.extensions

import commentaries.models.Commentary
import commentaries.models.CommentaryDbModel

fun CommentaryDbModel.toCommentary() = Commentary(
    id,
    title,
    body,
    user.id
)