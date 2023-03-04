package programmingLanguages.models

data class ProgrammingLanguageCreationRequest(
    var name: String,
) {
    fun toCreationModel() = ProgrammingLanguageCreationModel(
        name = name
    )
}