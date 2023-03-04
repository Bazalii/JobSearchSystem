package databases.validators

import commonClasses.IThrowingValidator
import databases.models.Database
import exceptions.InvalidEntityException
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class DatabaseValidator : IThrowingValidator<Database> {

    override fun validate(checkedObject: Database) {
        if (checkedObject.name.isEmpty() || checkedObject.name.length > 30) {
            throw InvalidEntityException("Incorrect database name length! It should be less than 30 characters but more than 0.")
        }
    }
}