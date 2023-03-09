package resumes.extensions

import databases.extensions.toDatabaseResponse
import frameworks.extensions.toFrameworkResponse
import programmingLanguages.extensions.toProgrammingLanguageResponse
import projects.extensions.toProjectResponse
import resumes.models.Resume
import resumes.models.ResumeResponse
import workExperience.extensions.toWorkExperienceItemResponse

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
    projects.map { it.toProjectResponse() }.toMutableSet(),
    workExperiences.map { it.toWorkExperienceItemResponse() }.toMutableSet(),
    userId
)