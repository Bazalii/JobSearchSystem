package workExperience.repositories

import users.repositories.PanacheUserRepository
import workExperience.extensions.toWorkExperienceItem
import workExperience.models.WorkExperienceItem
import workExperience.models.WorkExperienceItemDbModel
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class WorkExperienceRepository(
    private var _panacheUserRepository: PanacheUserRepository,
    private var _panacheWorkExperienceRepository: PanacheWorkExperienceRepository,
) : IWorkExperienceRepository {

    override fun add(workExperienceItem: WorkExperienceItem) {
        _panacheWorkExperienceRepository.add(
            WorkExperienceItemDbModel(
                id = workExperienceItem.id,
                place = workExperienceItem.place,
                position = workExperienceItem.position,
                dates = workExperienceItem.dates,
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

    override fun update(workExperienceItem: WorkExperienceItem) {
        _panacheWorkExperienceRepository.update(
            WorkExperienceItemDbModel(
                id = workExperienceItem.id,
                place = workExperienceItem.place,
                position = workExperienceItem.position,
                dates = workExperienceItem.dates,
                user = _panacheUserRepository.getById(workExperienceItem.userId)
            )
        )
    }

    override fun removeById(id: UUID) {
        _panacheWorkExperienceRepository.removeById(id)
    }
}