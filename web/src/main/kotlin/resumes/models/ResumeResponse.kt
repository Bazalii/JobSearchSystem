package resumes.models

import java.util.*

data class ResumeResponse(
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
    var name: String = "",
    var currentJob: String = "",
    var quote: String = "",
    var languages: String = "",
    var frameworks: String = "",
    var databases: String = "",
    var otherTechnologies: String = "",
    var additionalInformation: String = "",
    var userId: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
)