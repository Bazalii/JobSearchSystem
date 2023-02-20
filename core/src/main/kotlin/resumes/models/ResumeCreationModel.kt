package resumes.models

import java.util.*

data class ResumeCreationModel(
    var name: String,
    var currentJob: String,
    var quote: String,
    var languages: String,
    var frameworks: String,
    var databases: String,
    var otherTechnologies: String,
    var additionalInformation: String,
    var userId: UUID
)