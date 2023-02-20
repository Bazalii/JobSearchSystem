package users.services

import users.models.User
import users.models.UserCreationModel
import java.util.*

interface IUserService {
    fun add(userCreationModel: UserCreationModel)
    fun getById(id: UUID): User
    fun updatePassword(userId: UUID, password: String)
    fun removeById(id: UUID)
}