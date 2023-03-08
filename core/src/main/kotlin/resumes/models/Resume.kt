package resumes.models

import databases.models.Database
import frameworks.models.Framework
import programmingLanguages.models.ProgrammingLanguage
import java.util.*

data class Resume(
    var id: UUID,
    var name: String,
    var currentJob: String,
    var quote: String,
    var languages: MutableSet<ProgrammingLanguage>,
    var frameworks: MutableSet<Framework>,
    var databases: MutableSet<Database>,
    var otherTechnologies: String,
    var additionalInformation: String,
    var userId: UUID,
)