package authentication.sessionTokens.services

import java.util.UUID

interface ISessionTokenService {
    fun refresh(userId: UUID): String
}