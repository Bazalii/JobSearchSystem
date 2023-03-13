package resumes.models

import databases.models.Database
import frameworks.models.Framework
import programmingLanguages.models.ProgrammingLanguage
import projects.models.Project
import workExperience.models.WorkExperienceItem
import java.util.*
import javax.persistence.FetchType
import javax.persistence.OneToMany

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
    var projects: MutableSet<Project> = mutableSetOf(),
    var workExperiences: MutableSet<WorkExperienceItem> = mutableSetOf(),
    var userId: UUID,
)