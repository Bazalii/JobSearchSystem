package databases.models

data class DatabaseCreationRequest(
    var name: String = "",
) {
    fun toCreationModel() = DatabaseCreationModel(
        name = name
    )
}