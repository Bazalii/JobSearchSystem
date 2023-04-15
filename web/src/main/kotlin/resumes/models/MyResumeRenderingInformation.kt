package resumes.models

import databases.models.Database
import frameworks.models.Framework
import programmingLanguages.models.ProgrammingLanguage
import projects.models.Project
import workExperience.models.WorkExperienceItem
import java.time.LocalDateTime

data class MyResumeRenderingInformation(
    val resume: Resume,
    val allLanguages: List<ProgrammingLanguage>,
    val allFrameworks: List<Framework>,
    val allDatabases: List<Database>,
    val projects: List<Project>,
    val workExperienceItems: List<WorkExperienceItem>,
    val requestStartTime: LocalDateTime,
)