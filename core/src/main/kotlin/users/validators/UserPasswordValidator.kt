package users.validators

import commonClasses.IThrowingValidator
import exceptions.InvalidEntityException
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserPasswordValidator : IThrowingValidator<String> {

    override fun validate(checkedObject: String) {
        if (checkedObject.length < 8 || checkedObject.length > 30) {
            throw InvalidEntityException("Incorrect password length! It should be less than 30 characters but more than 8.")
        }

        if (!checkedObject.contains("[a-z]".toRegex()) ||
            !checkedObject.contains("[A-Z]".toRegex()) ||
            !checkedObject.contains("[@#\$]".toRegex()) ||
            !checkedObject.contains("[1-9]".toRegex())
        ) {
            throw InvalidEntityException(
                "Password should contain at least one capital English letter, one lowercase" +
                        "English letter a number and special symbol(@, #, $)!"
            )
        }
    }
}