package workExperience.services.implementations

import workExperience.models.WorkExperienceItem
import workExperience.models.WorkExperienceItemCreationModel
import workExperience.repositories.IWorkExperienceRepository
import workExperience.services.IWorkExperienceService
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class WorkExperienceService(
    private var _workExperienceRepository: IWorkExperienceRepository,
) : IWorkExperienceService {

    override fun add(workExperienceItemCreationModel: WorkExperienceItemCreationModel) {
        _workExperienceRepository.add(
            WorkExperienceItem(
                id = UUID.randomUUID(),
                place = workExperienceItemCreationModel.place,
                position = workExperienceItemCreationModel.position,
                startDate = workExperienceItemCreationModel.startDate,
                endDate = workExperienceItemCreationModel.endDate,
                userId = workExperienceItemCreationModel.userId
            )
        )
    }

    override fun getById(id: UUID): WorkExperienceItem {
        return _workExperienceRepository.getById(id)
    }

    override fun getAllByUserId(id: UUID): List<WorkExperienceItem> {
        return _workExperienceRepository.getAllByUserId(id)
    }

    override fun update(workExperienceItem: WorkExperienceItem) {
        _workExperienceRepository.update(workExperienceItem)
    }

    override fun removeById(id: UUID) {
        _workExperienceRepository.removeById(id)
    }
}