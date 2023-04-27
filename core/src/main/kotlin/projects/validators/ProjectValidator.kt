package projects.validators

import commonClasses.IThrowingValidator
import exceptions.InvalidEntityException
import jakarta.enterprise.context.ApplicationScoped
import projects.models.Project

@ApplicationScoped
class ProjectValidator : IThrowingValidator<Project> {

    override fun validate(checkedObject: Project) {
        if (!checkedObject.name.matches("[a-zA-Z]*".toRegex())) {
            throw InvalidEntityException("Project name should consist only of English letters!")
        }

        if (checkedObject.name.length < 5 || checkedObject.name.length > 30) {
            throw InvalidEntityException("Incorrect project name length! It should be less than 30 characters but more than 5.")
        }

        if (checkedObject.link.length < 4 || checkedObject.link.length > 200) {
            throw InvalidEntityException("Incorrect project link length! It should be less than 200 characters but more than 4.")
        }

        if (checkedObject.year < 1843) {
            throw InvalidEntityException("Programming did not exist back to that days.")
        }
    }
}