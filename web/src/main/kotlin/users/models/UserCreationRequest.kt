package users.models

data class UserCreationRequest(
    var login: String = "",
    var password: String = "",
){
    fun toCreationModel() = UserCreationModel(
        login = login,
        password = password
    )
}
