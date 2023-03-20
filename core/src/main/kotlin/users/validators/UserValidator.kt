package users.validators

import commonClasses.IThrowingValidator
import exceptions.EntityAlreadyExistsException
import exceptions.EntityNotFoundException
import exceptions.InvalidEntityException
import users.models.UserCreationModel
import users.repositories.IUserRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserValidator(
    private val _userRepository: IUserRepository,
    private val _userPasswordValidator: UserPasswordValidator,
) : IThrowingValidator<UserCreationModel> {

    override fun validate(checkedObject: UserCreationModel) {
        try {
            _userRepository.getByLogin(checkedObject.login)
            throw EntityAlreadyExistsException("This login is taken!")
        } catch (_: EntityNotFoundException) {
        }

        try {
            _userRepository.getByEmail(checkedObject.email)
            throw EntityAlreadyExistsException("This email is taken!")
        } catch (_: EntityNotFoundException) {
        }

        if (checkedObject.login.length < 5 || checkedObject.login.length > 30) {
            throw InvalidEntityException("Incorrect login length! It should be less than 30 characters but more than 5.")
        }

        if (!checkedObject.login.matches("[a-zA-Z@\$]*".toRegex())) {
            throw InvalidEntityException("Login should contain only english letters, @ and $!")
        }

        _userPasswordValidator.validate(checkedObject.password)
    }
}