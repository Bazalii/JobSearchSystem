package workExperience.models

import java.time.LocalDate
import java.util.*

data class WorkExperienceItemResponse(
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
    var place: String = "",
    var position: String,
    var startDate: LocalDate = LocalDate.MIN,
    var endDate: LocalDate = LocalDate.MAX,
    var userId: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
)