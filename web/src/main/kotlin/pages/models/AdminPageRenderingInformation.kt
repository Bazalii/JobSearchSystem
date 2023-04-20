package pages.models

import databases.models.Database
import frameworks.models.Framework
import programmingLanguages.models.ProgrammingLanguage
import java.time.LocalDateTime

data class AdminPageRenderingInformation(
    val allLanguages: List<ProgrammingLanguage>,
    val allFrameworks: List<Framework>,
    val allDatabases: List<Database>,
    val requestStartTime: LocalDateTime,
)