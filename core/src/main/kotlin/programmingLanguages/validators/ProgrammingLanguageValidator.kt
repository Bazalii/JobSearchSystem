package programmingLanguages.validators

import commonClasses.IThrowingValidator
import exceptions.InvalidEntityException
import programmingLanguages.models.ProgrammingLanguage
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProgrammingLanguageValidator : IThrowingValidator<ProgrammingLanguage> {

    override fun validate(checkedObject: ProgrammingLanguage) {
        if (checkedObject.name.isEmpty() || checkedObject.name.length > 30) {
            throw InvalidEntityException("Incorrect language name length! It should be less than 30 characters but more than 0.")
        }
    }
}