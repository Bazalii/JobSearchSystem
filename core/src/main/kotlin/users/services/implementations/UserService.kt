package users.services.implementations

import commonClasses.hashing.IHashCreator
import commonClasses.IThrowingValidator
import users.models.User
import users.models.UserCreationModel
import users.repositories.IUserRepository
import users.services.IUserService
import users.validators.UserPasswordValidator
import users.validators.UserRoleValidator
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserService(
    private var _userRepository: IUserRepository,
    private var _userValidator: IThrowingValidator<UserCreationModel>,
    private val _userPasswordValidator: UserPasswordValidator,
    private val _userRoleValidator: UserRoleValidator,
    private val _hashCreator: IHashCreator,
) : IUserService {

    override fun add(userCreationModel: UserCreationModel): User {
        _userValidator.validate(userCreationModel)

        userCreationModel.password = _hashCreator.hash(userCreationModel.password)

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

    override fun updateRole(login: String, role: String): User {
        _userRoleValidator.validate(role)

        return _userRepository.updateRole(login, role)
    }

    override fun removeById(id: UUID): User {
        return _userRepository.removeById(id)
    }
}