package users.repositories

import users.models.User
import java.util.*

interface IUserRepository {
    fun add(user: User)
    fun getById(id: UUID): User
    fun getByLogin(login: String): User
    fun updatePassword(userId: UUID, password: String)
    fun removeById(id: UUID)
}