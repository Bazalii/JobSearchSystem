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

    override fun add(userCreationModel: UserCreationModel) {
        _userValidator.validate(userCreationModel)

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
        _userPasswordValidator.validate(password)

        _userRepository.updatePassword(userId, password)
    }

    override fun removeById(id: UUID) {
        _userRepository.removeById(id)
    }
}