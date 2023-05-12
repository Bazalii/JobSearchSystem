package authentication.refreshTokens.services

import authentication.refreshTokens.models.UserCredentials

interface IRefreshTokenService {
    fun login(credentials: UserCredentials): String
}