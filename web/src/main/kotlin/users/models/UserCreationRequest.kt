package users.models

data class UserCreationRequest(
    var login: String,
    var password: String,
)
