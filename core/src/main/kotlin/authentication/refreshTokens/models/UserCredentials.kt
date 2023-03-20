package authentication.refreshTokens.models

data class UserCredentials(
    var uniqueIdentifier: String = "",
    var password: String = "",
)