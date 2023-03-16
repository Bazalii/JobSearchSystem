package authentication.refreshTokens.services

import authentication.refreshTokens.models.UserCredentials
import org.eclipse.microprofile.jwt.JsonWebToken

interface IRefreshTokenService {
    fun login(credentials: UserCredentials): String
}