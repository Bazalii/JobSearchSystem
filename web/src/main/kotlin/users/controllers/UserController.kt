package users.controllers

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
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

    @APIResponses(
        APIResponse(responseCode = "200", description = "User is created"),
        APIResponse(responseCode = "400", description = "Invalid login or password"),
        APIResponse(responseCode = "409", description = "Login is already taken")
    )
    @POST
    fun add(userCreationRequest: UserCreationRequest): UserResponse {
        return _userService.add(
            UserCreationModel(
                login = userCreationRequest.login,
                password = userCreationRequest.password
            )
        ).toUserResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "User with sent id does not exist")
    )
    @GET
    @Path("/{id}")
    fun getById(id: UUID): UserResponse {
        return _userService.getById(id).toUserResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "400", description = "Invalid password"),
        APIResponse(responseCode = "404", description = "User with sent id does not exist")
    )
    @PUT
    fun updatePassword(updatePasswordRequest: UpdatePasswordRequest): UserResponse {
        return _userService.updatePassword(updatePasswordRequest.id, updatePasswordRequest.password).toUserResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "User with sent id does not exist")
    )
    @DELETE
    @Path("/{id}")
    fun removeById(id: UUID): UserResponse {
        return _userService.removeById(id).toUserResponse()
    }
}