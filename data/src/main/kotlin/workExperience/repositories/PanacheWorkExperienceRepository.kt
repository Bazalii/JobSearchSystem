package workExperience.repositories

import exceptions.EntityNotFoundException
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import workExperience.models.WorkExperienceItemDbModel
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PanacheWorkExperienceRepository : PanacheRepositoryBase<WorkExperienceItemDbModel, UUID> {

    fun add(workExperienceItemDbModel: WorkExperienceItemDbModel): WorkExperienceItemDbModel {
        persist(workExperienceItemDbModel)

        return workExperienceItemDbModel
    }

    fun getById(id: UUID): WorkExperienceItemDbModel {
        return findById(id) ?: throw EntityNotFoundException("Entity not found!")
    }

    fun getAllByResumeId(id: UUID): List<WorkExperienceItemDbModel> {
        return list("resume_id", id)
    }

    fun update(workExperienceItemDbModel: WorkExperienceItemDbModel): WorkExperienceItemDbModel {
        val dbModel = findById(workExperienceItemDbModel.id) ?: throw EntityNotFoundException("Entity not found!")

        dbModel.place = workExperienceItemDbModel.place
        dbModel.position = workExperienceItemDbModel.position
        dbModel.startDate = workExperienceItemDbModel.startDate
        dbModel.endDate = workExperienceItemDbModel.endDate

        return dbModel
    }

    fun removeById(id: UUID): WorkExperienceItemDbModel {
        val dbModel = getById(id)

        deleteById(id)

        return dbModel
    }
}