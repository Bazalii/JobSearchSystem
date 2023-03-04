package programmingLanguages.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "\"programming_languages\"")
data class ProgrammingLanguageDbModel(
    @Id
    var id: UUID,
    var name: String
)