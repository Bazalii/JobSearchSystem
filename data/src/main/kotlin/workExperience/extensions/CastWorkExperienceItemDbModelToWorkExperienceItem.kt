package workExperience.extensions

import workExperience.models.WorkExperienceItem
import workExperience.models.WorkExperienceItemDbModel

fun WorkExperienceItemDbModel.toWorkExperienceItem() = WorkExperienceItem(
    id,
    place,
    position,
    startDate,
    endDate,
    resume.id
)