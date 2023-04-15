package resumes.models

import java.time.LocalDateTime
import java.util.*

data class ResumesRenderingInformation(
    val resumes: List<Resume>,
    val renderingPageNumber: Int,
    val numberOfPages: Int,
    val groups: Set<String>,
    val currentUserId: UUID,
    val requestStartTime: LocalDateTime
)
