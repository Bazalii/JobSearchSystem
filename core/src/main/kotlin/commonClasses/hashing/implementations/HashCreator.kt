package commonClasses.hashing.implementations

import commonClasses.hashing.IHashCreator
import io.quarkus.elytron.security.common.BcryptUtil
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class HashCreator : IHashCreator {

    override fun hash(input: String): String {
        return BcryptUtil.bcryptHash(input, 10, "passpasspasspass".toByteArray())
    }
}