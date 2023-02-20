package users.services.implementations

import users.models.User
import users.models.UserCreationModel
import users.repositories.IUserRepository
import users.services.IUserService
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserService(
    private var _userRepository: IUserRepository,
) : IUserService {

    override fun add(userCreationModel: UserCreationModel) {
        _userRepository.add(
            User(
                id = UUID.randomUUID(),
                login = userCreationModel.login,
                password = userCreationModel.password
            )
        )
    }

    override fun getById(id: UUID): User {
        return _userRepository.getById(id)
    }

    override fun updatePassword(userId: UUID, password: String) {
        _userRepository.updatePassword(userId, password)
    }

    override fun removeById(id: UUID) {
        _userRepository.removeById(id)
    }
}