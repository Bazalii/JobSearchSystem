package users.controllers

import users.extensions.toUserResponse
import users.models.*
import users.services.IUserService
import java.util.*
import javax.enterprise.context.RequestScoped
import javax.ws.rs.*

@RequestScoped
@Path("users")
class UserController(
    private var _userService: IUserService,
) {

    @POST
    fun add(userCreationRequest: UserCreationRequest): UserResponse {
        return _userService.add(
            UserCreationModel(
                login = userCreationRequest.login,
                password = userCreationRequest.password
            )
        ).toUserResponse()
    }

    @GET
    @Path("/{id}")
    fun getById(id: UUID): UserResponse {
        return _userService.getById(id).toUserResponse()
    }

    @PUT
    fun updatePassword(updatePasswordRequest: UpdatePasswordRequest): UserResponse {
        return _userService.updatePassword(updatePasswordRequest.id, updatePasswordRequest.password).toUserResponse()
    }

    @DELETE
    @Path("/{id}")
    fun removeById(id: UUID): UserResponse {
        return _userService.removeById(id).toUserResponse()
    }
}