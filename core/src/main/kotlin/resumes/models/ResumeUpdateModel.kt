package resumes.models

import java.util.*

data class ResumeUpdateModel(
    var id: UUID,
    var name: String,
    var currentJob: String,
    var quote: String,
    var languages: MutableSet<UUID>,
    var frameworks: MutableSet<UUID>,
    var databases: MutableSet<UUID>,
    var otherTechnologies: String,
    var additionalInformation: String,
    var userId: UUID,
)
