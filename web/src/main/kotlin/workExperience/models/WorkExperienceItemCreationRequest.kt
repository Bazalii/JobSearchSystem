package workExperience.models

import java.util.*

data class WorkExperienceItemCreationRequest(
    var place: String,
    var position: String,
    var dates: String,
    var userId: UUID,
)
