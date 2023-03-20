package users.repositories

import users.models.User
import java.util.*

interface IUserRepository {
    fun add(user: User): User
    fun getById(id: UUID): User
    fun getByLogin(login: String): User
    fun getByEmail(email: String): User
    fun updatePassword(userId: UUID, password: String): User
    fun updateRole(login: String, role: String): User
    fun removeById(id: UUID): User
}