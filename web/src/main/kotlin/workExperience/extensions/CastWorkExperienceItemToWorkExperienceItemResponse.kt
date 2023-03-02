package workExperience.extensions

import workExperience.models.WorkExperienceItem
import workExperience.models.WorkExperienceItemResponse

fun WorkExperienceItem.toWorkExperienceItemResponse() = WorkExperienceItemResponse(
    id,
    place,
    position,
    startDate,
    endDate,
    userId
)