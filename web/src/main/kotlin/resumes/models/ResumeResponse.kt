package resumes.models

import databases.models.DatabaseResponse
import frameworks.models.FrameworkResponse
import programmingLanguages.models.ProgrammingLanguageResponse
import java.util.*

data class ResumeResponse(
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
    var name: String = "",
    var currentJob: String = "",
    var quote: String = "",
    var languages: MutableSet<ProgrammingLanguageResponse> = hashSetOf(),
    var frameworks: MutableSet<FrameworkResponse> = hashSetOf(),
    var databases: MutableSet<DatabaseResponse> = hashSetOf(),
    var otherTechnologies: String = "",
    var additionalInformation: String = "",
    var userId: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
)