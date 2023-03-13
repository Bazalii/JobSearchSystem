package commentaries.validators

import commentaries.models.Commentary
import commonClasses.IThrowingValidator
import exceptions.InvalidEntityException
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CommentaryValidator : IThrowingValidator<Commentary> {

    override fun validate(checkedObject: Commentary) {
        if (!checkedObject.title.matches("[a-zA-Z]*".toRegex())) {
            throw InvalidEntityException("Commentary title should consist only of English letters!")
        }

        if (checkedObject.title.length < 5 || checkedObject.title.length > 30) {
            throw InvalidEntityException("Incorrect commentary title length! It should be less than 30 characters but more than 5.")
        }

        if (!checkedObject.body.matches("[a-zA-Z@%#\$]*".toRegex())) {
            throw InvalidEntityException("Commentary title should consist only of English letters, @, %, # and $!")
        }

        if (checkedObject.body.length < 5 || checkedObject.body.length > 300) {
            throw InvalidEntityException("Incorrect login length! It should be less than 300 characters but more than 5.")
        }
    }
}