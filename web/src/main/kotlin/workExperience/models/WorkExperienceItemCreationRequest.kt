package workExperience.models

import java.time.LocalDate
import java.util.*

data class WorkExperienceItemCreationRequest(
    var place: String = "",
    var position: String = "",
    var startDate: LocalDate = LocalDate.MIN,
    var endDate: LocalDate = LocalDate.MAX,
    var userId: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
){
    fun toCreationModel() = WorkExperienceItemCreationModel(
        place = place,
        position = position,
        startDate = startDate,
        endDate = endDate,
        userId = userId
    )
}
