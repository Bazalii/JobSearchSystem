package resumes.models

import databases.models.Database
import frameworks.models.Framework
import programmingLanguages.models.ProgrammingLanguage
import projects.models.Project
import workExperience.models.WorkExperienceItem
import java.util.*

data class Resume(
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
    var name: String = "name",
    var currentJob: String = "job",
    var quote: String = "quote",
    var languages: MutableSet<ProgrammingLanguage> = hashSetOf(),
    var frameworks: MutableSet<Framework> = hashSetOf(),
    var databases: MutableSet<Database> = hashSetOf(),
    var otherTechnologies: String = "other technologies",
    var additionalInformation: String = "additional information",
    var projects: MutableSet<Project> = mutableSetOf(),
    var workExperiences: MutableSet<WorkExperienceItem> = mutableSetOf(),
    var userId: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
)