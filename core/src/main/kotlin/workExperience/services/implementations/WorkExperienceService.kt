package workExperience.services.implementations

import commonClasses.IThrowingValidator
import workExperience.models.WorkExperienceItem
import workExperience.models.WorkExperienceItemCreationModel
import workExperience.repositories.IWorkExperienceRepository
import workExperience.services.IWorkExperienceService
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class WorkExperienceService(
    private var _workExperienceRepository: IWorkExperienceRepository,
    private var _workExperienceItemValidator: IThrowingValidator<WorkExperienceItem>,
) : IWorkExperienceService {

    override fun add(workExperienceItemCreationModel: WorkExperienceItemCreationModel): WorkExperienceItem {
        val workExperienceItem = workExperienceItemCreationModel.toWorkExperienceItem()

        _workExperienceItemValidator.validate(workExperienceItem)

        return _workExperienceRepository.add(workExperienceItem)
    }

    override fun getById(id: UUID): WorkExperienceItem {
        return _workExperienceRepository.getById(id)
    }

    override fun getAllByUserId(id: UUID): List<WorkExperienceItem> {
        return _workExperienceRepository.getAllByUserId(id)
    }

    override fun update(workExperienceItem: WorkExperienceItem): WorkExperienceItem {
        _workExperienceItemValidator.validate(workExperienceItem)

        return _workExperienceRepository.update(workExperienceItem)
    }

    override fun removeById(id: UUID): WorkExperienceItem {
        return _workExperienceRepository.removeById(id)
    }
}