package frameworks.models

data class FrameworkCreationRequest(
    var name: String = "",
) {
    fun toCreationModel() = FrameworkCreationModel(
        name = name
    )
}