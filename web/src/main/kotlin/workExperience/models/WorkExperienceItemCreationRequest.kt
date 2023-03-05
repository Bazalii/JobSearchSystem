package workExperience.models

import java.time.LocalDate
import java.util.*

data class WorkExperienceItemCreationRequest(
    var place: String,
    var position: String,
    var startDate: LocalDate,
    var endDate: LocalDate,
    var userId: UUID,
){
    fun toCreationModel() = WorkExperienceItemCreationModel(
        place = place,
        position = position,
        startDate = startDate,
        endDate = endDate,
        userId = userId
    )
}
