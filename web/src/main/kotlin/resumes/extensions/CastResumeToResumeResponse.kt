package resumes.extensions

import databases.extensions.toDatabaseResponse
import frameworks.extensions.toFrameworkResponse
import programmingLanguages.extensions.toProgrammingLanguageResponse
import resumes.models.Resume
import resumes.models.ResumeResponse

fun Resume.toResumeResponse() = ResumeResponse(
    id,
    name,
    currentJob,
    quote,
    languages.map { it.toProgrammingLanguageResponse() }.toHashSet(),
    frameworks.map { it.toFrameworkResponse() }.toHashSet(),
    databases.map { it.toDatabaseResponse() }.toHashSet(),
    otherTechnologies,
    additionalInformation,
    userId
)