package users.validators

import commonClasses.IThrowingValidator
import exceptions.InvalidEntityException
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserPasswordValidator : IThrowingValidator<String> {

    override fun validate(checkedObject: String) {
        if (!checkedObject.matches("[a-z]*".toRegex()) ||
            !checkedObject.matches("[A-Z]*".toRegex()) ||
            !checkedObject.matches("[@#\$]*".toRegex()) ||
            !checkedObject.matches("[1-9]*".toRegex())
        ) {
            throw InvalidEntityException(
                "Password should contain at least one capital English letter, one lowercase" +
                        "English letter a number and special symbol(@, #, $)!"
            )
        }
    }
}