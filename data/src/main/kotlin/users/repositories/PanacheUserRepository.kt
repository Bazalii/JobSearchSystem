package users.repositories

import exceptions.EntityNotFoundException
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import users.models.UserDbModel
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PanacheUserRepository : PanacheRepositoryBase<UserDbModel, UUID> {

    fun add(userDbModel: UserDbModel): UserDbModel {
        persist(userDbModel)

        return userDbModel
    }

    fun getById(id: UUID): UserDbModel {
        return findById(id) ?: throw EntityNotFoundException("Entity not found!")
    }

    fun getByLogin(login: String): UserDbModel {
        return find("login", login).firstResult() ?: throw EntityNotFoundException("User not found!")
    }

    fun getByEmail(email: String): UserDbModel {
        return find("email", email).firstResult() ?: throw EntityNotFoundException("User not found!")
    }

    fun removeById(id: UUID): UserDbModel {
        val dbModel = getById(id)

        deleteById(id)

        return dbModel
    }

    fun updateRole(login: String, role: String): UserDbModel {
        val dbModel = getByLogin(login)

        dbModel.role = role

        return dbModel
    }


    fun updatePassword(userId: UUID, password: String): UserDbModel {
        val dbModel = findById(userId) ?: throw EntityNotFoundException("Entity not found!")

        dbModel.password = password

        return dbModel
    }
}