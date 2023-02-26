package users.repositories

import users.extensions.toUser
import users.extensions.toUserDbModel
import users.models.User
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class UserRepository(
    private val _panacheUserRepository: PanacheUserRepository,
) : IUserRepository {

    @Transactional
    override fun add(user: User) {
        _panacheUserRepository.add(user.toUserDbModel())
    }

    override fun getById(id: UUID): User {
        val userDbModel = _panacheUserRepository.getById(id)

        return userDbModel.toUser()
    }

    override fun getByLogin(login: String): User {
        val userDbModel = _panacheUserRepository.getByLogin(login)

        return userDbModel.toUser()
    }

    @Transactional
    override fun updatePassword(userId: UUID, password: String) {
        _panacheUserRepository.updatePassword(userId, password)
    }

    @Transactional
    override fun removeById(id: UUID) {
        _panacheUserRepository.removeById(id)
    }
}