package workExperience.validators

import commonClasses.IThrowingValidator
import exceptions.InvalidEntityException
import workExperience.models.WorkExperienceItem
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class WorkExperienceItemValidator : IThrowingValidator<WorkExperienceItem> {

    override fun validate(checkedObject: WorkExperienceItem) {
        if (checkedObject.place.length < 3 || checkedObject.place.length > 50) {
            throw InvalidEntityException("Incorrect place name length! It should be less than 50 characters but more than 2.")
        }

        if (checkedObject.position.length < 5 || checkedObject.position.length > 30) {
            throw InvalidEntityException("Incorrect position name length! It should be less than 30 characters but more than 5.")
        }

        if (checkedObject.endDate < checkedObject.startDate) {
            throw InvalidEntityException("End date cannot be earlier than start date!")
        }
    }
}