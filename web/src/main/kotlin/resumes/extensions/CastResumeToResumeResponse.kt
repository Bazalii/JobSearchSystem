package resumes.extensions

import resumes.models.Resume
import resumes.models.ResumeResponse

fun Resume.toResumeResponse() = ResumeResponse(
    id,
    name,
    currentJob,
    quote,
    languages,
    frameworks,
    databases,
    otherTechnologies,
    additionalInformation,
    userId
)