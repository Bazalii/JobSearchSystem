package commentaries.extensions

import commentaries.models.Commentary
import commentaries.models.CommentaryResponse

fun Commentary.toResponseCommentary() = CommentaryResponse(
    id,
    title,
    body,
    userId
)