package users.services.implementations

import commonClasses.IThrowingValidator
import users.models.User
import users.models.UserCreationModel
import users.repositories.IUserRepository
import users.services.IUserService
import users.validators.UserPasswordValidator
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserService(
    private var _userRepository: IUserRepository,
    private var _userValidator: IThrowingValidator<UserCreationModel>,
    private val _userPasswordValidator: UserPasswordValidator,
) : IUserService {

    override fun add(userCreationModel: UserCreationModel): User {
        _userValidator.validate(userCreationModel)

        return _userRepository.add(userCreationModel.toUser())
    }

    override fun getById(id: UUID): User {
        return _userRepository.getById(id)
    }

    override fun getByLogin(login: String): User {
        return _userRepository.getByLogin(login)
    }

    override fun updatePassword(userId: UUID, password: String): User {
        _userPasswordValidator.validate(password)

        return _userRepository.updatePassword(userId, password)
    }

    override fun makeUserAdmin(login: String): User {
        return _userRepository.updateRole(login, "Admin")
    }

    override fun removeById(id: UUID): User {
        return _userRepository.removeById(id)
    }
}