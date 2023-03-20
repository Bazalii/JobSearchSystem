package users.services

import users.models.User
import users.models.UserCreationModel
import java.util.*

interface IUserService {
    fun add(userCreationModel: UserCreationModel): User
    fun getById(id: UUID): User
    fun getByLogin(login: String): User
    fun updatePassword(userId: UUID, password: String): User
    fun makeUserAdmin(login: String): User
    fun removeById(id: UUID): User
}