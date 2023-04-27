package frameworks.validators

import commonClasses.IThrowingValidator
import exceptions.InvalidEntityException
import frameworks.models.Framework
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class FrameworkValidator : IThrowingValidator<Framework> {

    override fun validate(checkedObject: Framework) {
        if (checkedObject.name.isEmpty() || checkedObject.name.length > 30) {
            throw InvalidEntityException("Incorrect framework name length! It should be less than 30 characters but more than 0.")
        }
    }
}