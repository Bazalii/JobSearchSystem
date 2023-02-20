package resumes.extensions

import resumes.models.Resume
import resumes.models.ResumeDbModel

fun ResumeDbModel.toResume() = Resume(
    id,
    name,
    currentJob,
    quote,
    languages,
    frameworks,
    databases,
    otherTechnologies,
    additionalInformation,
    user.id
)