package resumes.extensions

import databases.extensions.toDatabase
import frameworks.extensions.toFramework
import programmingLanguages.extensions.toProgrammingLanguage
import projects.extensions.toProject
import resumes.models.Resume
import resumes.models.ResumeDbModel
import workExperience.extensions.toWorkExperienceItem

fun ResumeDbModel.toResume() = Resume(
    id,
    name,
    currentJob,
    quote,
    languages.map { it.toProgrammingLanguage() }.toMutableSet(),
    frameworks.map { it.toFramework() }.toMutableSet(),
    databases.map { it.toDatabase() }.toMutableSet(),
    otherTechnologies,
    additionalInformation,
    projects.map { it.toProject() }.toMutableSet(),
    workExperiences.map { it.toWorkExperienceItem() }.toMutableSet(),
    user.id
)