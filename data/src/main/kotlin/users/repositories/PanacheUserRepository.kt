package users.repositories

import exceptions.EntityNotFoundException
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import users.models.UserDbModel
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PanacheUserRepository : PanacheRepositoryBase<UserDbModel, UUID> {

    fun add(user: UserDbModel) {
        persist(user)
    }

    fun getById(id: UUID): UserDbModel {
        return findById(id) ?: throw EntityNotFoundException("Entity not found!")
    }

    fun getByLogin(login: String): UserDbModel {
        return list("login", login).firstOrNull() ?: throw EntityNotFoundException("User not found!")
    }

    fun removeById(id: UUID) {
        val user = getById(id)

        deleteById(id)
    }

    fun updatePassword(userId: UUID, password: String) {
        val userDbModel =
            findById(userId) ?: throw EntityNotFoundException("Entity not found!")

        userDbModel.password = password
    }
}