package workExperience.repositories

import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import resumes.repositories.PanacheResumeRepository
import workExperience.extensions.toWorkExperienceItem
import workExperience.models.WorkExperienceItem
import workExperience.models.WorkExperienceItemDbModel
import java.util.*

@ApplicationScoped
class WorkExperienceRepository(
    private var _panacheResumeRepository: PanacheResumeRepository,
    private var _panacheWorkExperienceRepository: PanacheWorkExperienceRepository,
) : IWorkExperienceRepository {

    @Transactional
    override fun add(workExperienceItem: WorkExperienceItem): WorkExperienceItem {
        return _panacheWorkExperienceRepository.add(
            WorkExperienceItemDbModel(
                id = workExperienceItem.id,
                place = workExperienceItem.place,
                position = workExperienceItem.position,
                startDate = workExperienceItem.startDate,
                endDate = workExperienceItem.endDate,
                resume = _panacheResumeRepository.getById(workExperienceItem.resumeId)
            )
        ).toWorkExperienceItem()
    }

    override fun getById(id: UUID): WorkExperienceItem {
        val dbModel = _panacheWorkExperienceRepository.getById(id)

        return dbModel.toWorkExperienceItem()
    }

    override fun getAllByResumeId(id: UUID): List<WorkExperienceItem> {
        val dbModels = _panacheWorkExperienceRepository.getAllByResumeId(id)

        return dbModels.map { it.toWorkExperienceItem() }
    }

    @Transactional
    override fun update(workExperienceItem: WorkExperienceItem): WorkExperienceItem {
        return _panacheWorkExperienceRepository.update(
            WorkExperienceItemDbModel(
                id = workExperienceItem.id,
                place = workExperienceItem.place,
                position = workExperienceItem.position,
                startDate = workExperienceItem.startDate,
                endDate = workExperienceItem.endDate,
                resume = _panacheResumeRepository.getById(workExperienceItem.resumeId)
            )
        ).toWorkExperienceItem()
    }

    @Transactional
    override fun removeById(id: UUID): WorkExperienceItem {
        return _panacheWorkExperienceRepository.removeById(id).toWorkExperienceItem()
    }
}