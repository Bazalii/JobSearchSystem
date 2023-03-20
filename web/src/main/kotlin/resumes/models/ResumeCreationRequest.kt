package resumes.models

import java.util.*

data class ResumeCreationRequest(
    var name: String = "",
    var currentJob: String = "",
    var quote: String = "",
    var languages: MutableSet<UUID> = mutableSetOf(),
    var frameworks: MutableSet<UUID> = mutableSetOf(),
    var databases: MutableSet<UUID> = mutableSetOf(),
    var otherTechnologies: String = "",
    var additionalInformation: String = "",
) {
    fun toCreationModel(userId: UUID) = ResumeCreationModel(
        name = name,
        currentJob = currentJob,
        quote = quote,
        languages = languages,
        frameworks = frameworks,
        databases = databases,
        otherTechnologies = otherTechnologies,
        additionalInformation = additionalInformation,
        userId = userId
    )
}
