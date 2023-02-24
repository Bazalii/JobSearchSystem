package users.controllers

import users.models.*
import users.services.IUserService
import java.util.*
import javax.enterprise.context.RequestScoped
import javax.ws.rs.*

@RequestScoped
@Path("/users")
class UserController(
    private var _userService: IUserService,
) {

    @POST
    fun add(userCreationRequest: UserCreationRequest) {
        _userService.add(
            UserCreationModel(
                login = userCreationRequest.login,
                password = userCreationRequest.password
            )
        )
    }

    @GET
    @Path("/{id}")
    fun getById(id: UUID): UserResponse {
        val user = _userService.getById(id)

        return UserResponse(
            id = user.id,
            login = user.login,
            password = user.password
        )
    }

    @PUT
    fun updatePassword(updatePasswordRequest: UpdatePasswordRequest) {
        _userService.updatePassword(updatePasswordRequest.id, updatePasswordRequest.password)
    }

    @DELETE
    @Path("/{id}")
    fun removeById(id: UUID) {
        _userService.removeById(id)
    }
}