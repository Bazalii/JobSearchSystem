package workExperience.repositories

import users.repositories.PanacheUserRepository
import workExperience.extensions.toWorkExperienceItem
import workExperience.models.WorkExperienceItem
import workExperience.models.WorkExperienceItemDbModel
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class WorkExperienceRepository(
    private var _panacheUserRepository: PanacheUserRepository,
    private var _panacheWorkExperienceRepository: PanacheWorkExperienceRepository,
) : IWorkExperienceRepository {

    @Transactional
    override fun add(workExperienceItem: WorkExperienceItem) {
        _panacheWorkExperienceRepository.add(
            WorkExperienceItemDbModel(
                id = workExperienceItem.id,
                place = workExperienceItem.place,
                position = workExperienceItem.position,
                startDate = workExperienceItem.startDate,
                endDate = workExperienceItem.endDate,
                user = _panacheUserRepository.getById(workExperienceItem.userId)
            )
        )
    }

    override fun getById(id: UUID): WorkExperienceItem {
        val dbModel = _panacheWorkExperienceRepository.getById(id)

        return dbModel.toWorkExperienceItem()
    }

    override fun getAllByUserId(id: UUID): List<WorkExperienceItem> {
        val dbModels = _panacheWorkExperienceRepository.getAllByUserId(id)

        return dbModels.map { it.toWorkExperienceItem() }
    }

    @Transactional
    override fun update(workExperienceItem: WorkExperienceItem) {
        _panacheWorkExperienceRepository.update(
            WorkExperienceItemDbModel(
                id = workExperienceItem.id,
                place = workExperienceItem.place,
                position = workExperienceItem.position,
                startDate = workExperienceItem.startDate,
                endDate = workExperienceItem.endDate,
                user = _panacheUserRepository.getById(workExperienceItem.userId)
            )
        )
    }

    @Transactional
    override fun removeById(id: UUID) {
        _panacheWorkExperienceRepository.removeById(id)
    }
}