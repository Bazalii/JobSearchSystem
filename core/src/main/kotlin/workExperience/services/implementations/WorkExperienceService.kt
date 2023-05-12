package workExperience.services.implementations

import commonClasses.IThrowingValidator
import jakarta.enterprise.context.ApplicationScoped
import workExperience.models.WorkExperienceItem
import workExperience.models.WorkExperienceItemCreationModel
import workExperience.repositories.IWorkExperienceRepository
import workExperience.services.IWorkExperienceService
import java.util.*

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

    override fun getAllByResumeId(id: UUID): List<WorkExperienceItem> {
        return _workExperienceRepository.getAllByResumeId(id)
    }

    override fun update(workExperienceItem: WorkExperienceItem): WorkExperienceItem {
        _workExperienceItemValidator.validate(workExperienceItem)

        return _workExperienceRepository.update(workExperienceItem)
    }

    override fun removeById(id: UUID): WorkExperienceItem {
        return _workExperienceRepository.removeById(id)
    }
}