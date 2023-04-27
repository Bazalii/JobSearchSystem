package authentication.sessionTokens.services

import java.util.*

interface ISessionTokenService {
    fun refresh(userId: UUID): String
}