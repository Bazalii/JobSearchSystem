package pages.models

import commentaries.models.Commentary

data class CommentaryRenderingInformation(
    val commentary: Commentary,
    val creatorLogin: String,
)
