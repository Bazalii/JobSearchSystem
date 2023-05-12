package authentication.refreshTokens.services.implemetations

import authentication.refreshTokens.models.UserCredentials
import authentication.refreshTokens.services.IRefreshTokenService
import commonClasses.hashing.IHashCreator
import exceptions.EntityNotFoundException
import exceptions.InvalidCredentialsException
import io.smallrye.jwt.algorithm.SignatureAlgorithm
import io.smallrye.jwt.build.Jwt
import io.smallrye.jwt.util.KeyUtils
import jakarta.enterprise.context.ApplicationScoped
import users.models.User
import users.repositories.IUserRepository

@ApplicationScoped
class RefreshTokenService(
    private var _userRepository: IUserRepository,
    private var _hashCreator: IHashCreator,
) : IRefreshTokenService {

    override fun login(credentials: UserCredentials): String {
        val user: User

        try {
            user =
                if (credentials.uniqueIdentifier.contains("@")) {
                    _userRepository.getByEmail(credentials.uniqueIdentifier)
                } else {
                    _userRepository.getByLogin(credentials.uniqueIdentifier)
                }
        } catch (exception: EntityNotFoundException) {
            throw InvalidCredentialsException("Invalid login!")
        }

        if (user.password != _hashCreator.hash(credentials.password)) {
            throw InvalidCredentialsException("Invalid password!")
        }

        return Jwt
            .issuer("cv-backend")
            .upn(user.id.toString())
            .expiresIn(604800)
            .sign(KeyUtils.readPrivateKey("privateKey.pem", SignatureAlgorithm.ES256))
    }
}