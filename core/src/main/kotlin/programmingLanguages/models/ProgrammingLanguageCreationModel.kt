package programmingLanguages.models

import java.util.*

data class ProgrammingLanguageCreationModel(
    var name: String,
) {
    fun toProgrammingLanguage(): ProgrammingLanguage {
        return ProgrammingLanguage(
            id = UUID.randomUUID(),
            name = name
        )
    }
}
