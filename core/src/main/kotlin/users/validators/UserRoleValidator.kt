package users.validators

import commonClasses.IThrowingValidator
import exceptions.InvalidEntityException
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserRoleValidator : IThrowingValidator<String> {

    override fun validate(checkedObject: String) {
        if (checkedObject != "User" &&
            checkedObject != "HR" &&
            checkedObject != "Admin"
        ) {
            throw InvalidEntityException("Invalid role!")
        }
    }
}