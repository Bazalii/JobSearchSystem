package commentaries.models

import java.time.LocalDateTime
import java.util.*

data class CommentariesRenderingInformation(
    val commentaries: List<Commentary>,
    val renderingPageNumber: Int,
    val numberOfPages: Int,
    val groups: Set<String>,
    val currentUserId: UUID,
    val requestStartTime: LocalDateTime
)
