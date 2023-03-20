package users.models

data class UserCreationRequest(
    var login: String = "",
    var email: String = "",
    var password: String = "",
    var role: String = "",
) {
    fun toCreationModel() = UserCreationModel(
        login = login,
        email = email,
        password = password,
        role = role
    )
}
