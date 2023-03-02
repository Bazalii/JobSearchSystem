package workExperience.repositories

import exceptions.EntityNotFoundException
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import workExperience.models.WorkExperienceItemDbModel
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PanacheWorkExperienceRepository : PanacheRepositoryBase<WorkExperienceItemDbModel, UUID> {

    fun add(workExperienceItemDbModel: WorkExperienceItemDbModel) {
        persist(workExperienceItemDbModel)
    }

    fun getById(id: UUID): WorkExperienceItemDbModel {
        return findById(id) ?: throw EntityNotFoundException("Entity not found!")
    }

    fun getAllByUserId(id: UUID): List<WorkExperienceItemDbModel> {
        return list("user_id", id)
    }

    fun update(workExperienceItemDbModel: WorkExperienceItemDbModel) {
        val dbModel = findById(workExperienceItemDbModel.id) ?: throw EntityNotFoundException("Entity not found!")

        dbModel.place = workExperienceItemDbModel.place
        dbModel.position = workExperienceItemDbModel.position
        dbModel.startDate = workExperienceItemDbModel.startDate
        dbModel.endDate = workExperienceItemDbModel.endDate
    }

    fun removeById(id: UUID) {
        val dbModel = getById(id)

        deleteById(id)
    }
}