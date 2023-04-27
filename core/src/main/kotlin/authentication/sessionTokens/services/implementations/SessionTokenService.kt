package authentication.sessionTokens.services.implementations

import authentication.sessionTokens.services.ISessionTokenService
import io.smallrye.jwt.algorithm.SignatureAlgorithm
import io.smallrye.jwt.build.Jwt
import io.smallrye.jwt.util.KeyUtils
import jakarta.enterprise.context.ApplicationScoped
import users.repositories.IUserRepository
import java.util.*

@ApplicationScoped
class SessionTokenService(
    private var _userRepository: IUserRepository,
) : ISessionTokenService {

    override fun refresh(userId: UUID): String {
        val user = _userRepository.getById(userId)

        return Jwt
            .issuer("cv-backend")
            .upn(userId.toString())
            .groups(user.role)
            .expiresIn(900)
            .sign(KeyUtils.readPrivateKey("privateKey.pem", SignatureAlgorithm.ES256))
    }
}