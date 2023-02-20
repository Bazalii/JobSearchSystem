package resumes.models

import java.util.*

data class ResumeCreationRequest(
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
