package workExperience.models

import java.util.*

data class WorkExperienceItemResponse(
    var id: UUID,
    var place: String,
    var position: String,
    var dates: String,
    var userId: UUID,
)