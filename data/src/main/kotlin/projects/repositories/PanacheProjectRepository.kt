package projects.repositories

import exceptions.EntityNotFoundException
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import projects.models.ProjectDbModel
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PanacheProjectRepository : PanacheRepositoryBase<ProjectDbModel, UUID> {

    fun add(projectDbModel: ProjectDbModel): ProjectDbModel {
        persist(projectDbModel)

        return projectDbModel
    }

    fun getById(id: UUID): ProjectDbModel {
        return findById(id) ?: throw EntityNotFoundException("Entity not found!")
    }

    fun getAllByResumeId(id: UUID): List<ProjectDbModel> {
        return list("resume_id", id)
    }

    fun update(projectDbModel: ProjectDbModel): ProjectDbModel {
        val dbModel = findById(projectDbModel.id) ?: throw EntityNotFoundException("Entity not found!")

        dbModel.name = projectDbModel.name
        dbModel.link = projectDbModel.link
        dbModel.year = projectDbModel.year

        return dbModel
    }

    fun removeById(id: UUID): ProjectDbModel {
        val dbModel = getById(id)

        deleteById(id)

        return dbModel
    }
}