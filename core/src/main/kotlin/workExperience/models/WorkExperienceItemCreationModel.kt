package workExperience.models

import java.time.LocalDate
import java.util.*

data class WorkExperienceItemCreationModel(
    var place: String,
    var position: String,
    var startDate: LocalDate,
    var endDate: LocalDate,
    var resumeId: UUID,
) {
    fun toWorkExperienceItem(): WorkExperienceItem {
        return WorkExperienceItem(
            id = UUID.randomUUID(),
            place = place,
            position = position,
            startDate = startDate,
            endDate = endDate,
            resumeId = resumeId
        )
    }
}