package resumes.validators

import commonClasses.IThrowingValidator
import exceptions.InvalidEntityException
import resumes.models.Resume
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ResumeValidator : IThrowingValidator<Resume> {

    override fun validate(checkedObject: Resume) {
        if (checkedObject.name.length < 3 || checkedObject.name.length > 50) {
            throw InvalidEntityException("Incorrect name length! It should be less than 50 characters but more than 2.")
        }

        if (checkedObject.currentJob.length < 5 || checkedObject.currentJob.length > 30) {
            throw InvalidEntityException("Incorrect job name length! It should be less than 30 characters but more than 4.")
        }

        if (checkedObject.quote.length < 5 || checkedObject.quote.length > 100) {
            throw InvalidEntityException("Incorrect quote length! It should be less than 100 characters but more than 4.")
        }

        if (checkedObject.languages.isEmpty() || checkedObject.languages.length > 200) {
            throw InvalidEntityException("Incorrect languages section length! It should be less than 200 characters but more than 0.")
        }

        if (checkedObject.frameworks.isEmpty() || checkedObject.frameworks.length > 200) {
            throw InvalidEntityException("Incorrect frameworks section length! It should be less than 200 characters but more than 0.")
        }

        if (checkedObject.databases.isEmpty() || checkedObject.databases.length > 200) {
            throw InvalidEntityException("Incorrect databases section length! It should be less than 200 characters but more than 0.")
        }

        if (checkedObject.otherTechnologies.length > 300) {
            throw InvalidEntityException("Incorrect other technologies section length! It should be less than 300 characters.")
        }

        if (checkedObject.additionalInformation.length > 300) {
            throw InvalidEntityException("Incorrect additional information section length! It should be less than 300 characters.")
        }
    }
}