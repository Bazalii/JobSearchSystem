package users.repositories

import users.extensions.toUser
import users.extensions.toUserDbModel
import users.models.User
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserRepository(
    private val _panacheUserRepository: PanacheUserRepository,
) : IUserRepository {

    override fun add(user: User) {
        _panacheUserRepository.add(user.toUserDbModel())
    }

    override fun getById(id: UUID): User {
        val userDbModel = _panacheUserRepository.getById(id)

        return userDbModel.toUser()
    }

    override fun updatePassword(userId: UUID, password: String) {
        _panacheUserRepository.updatePassword(userId, password)
    }

    override fun removeById(id: UUID) {
        _panacheUserRepository.removeById(id)
    }
}