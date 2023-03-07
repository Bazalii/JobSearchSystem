package resumes.extensions

import databases.extensions.toDatabase
import frameworks.extensions.toFramework
import programmingLanguages.extensions.toProgrammingLanguage
import resumes.models.Resume
import resumes.models.ResumeDbModel

fun ResumeDbModel.toResume() = Resume(
    id,
    name,
    currentJob,
    quote,
    languages.map { it.toProgrammingLanguage() }.toHashSet(),
    frameworks.map { it.toFramework() }.toHashSet(),
    databases.map { it.toDatabase() }.toHashSet(),
    otherTechnologies,
    additionalInformation,
    user.id
)