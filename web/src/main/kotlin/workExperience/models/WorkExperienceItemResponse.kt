package workExperience.models

import java.time.LocalDate
import java.util.*

data class WorkExperienceItemResponse(
    var id: UUID,
    var place: String,
    var position: String,
    var startDate: LocalDate,
    var endDate: LocalDate,
    var userId: UUID,
)