package users.controllers

import exceptions.InvalidEntityException
import org.eclipse.microprofile.jwt.Claim
import org.eclipse.microprofile.jwt.Claims
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import users.extensions.toUserResponse
import users.models.*
import users.services.IUserService
import java.util.*
import javax.annotation.security.RolesAllowed
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.*

@RequestScoped
@Path("users")
class UserController(
    private var _userService: IUserService,
) {

    @Inject
    @Claim(standard = Claims.upn)
    private lateinit var userId: String

    @APIResponses(
        APIResponse(responseCode = "200", description = "User is created"),
        APIResponse(responseCode = "400", description = "Invalid login, password or role"),
        APIResponse(responseCode = "409", description = "Login is already taken")
    )
    @POST
    fun add(userCreationRequest: UserCreationRequest): UserResponse {
        val creationModel = userCreationRequest.toCreationModel()

        if (creationModel.role != "HR" && creationModel.role != "User") {
            throw InvalidEntityException("Incorrect role!")
        }

        return _userService.add(creationModel).toUserResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "User with sent id does not exist")
    )
    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    fun getById(id: UUID): UserResponse {
        return _userService.getById(id).toUserResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "400", description = "Invalid password"),
        APIResponse(responseCode = "404", description = "User with sent id does not exist")
    )
    @PUT
    @RolesAllowed("User", "HR", "Admin")
    fun updatePassword(updatePasswordRequest: UpdatePasswordRequest): UserResponse {
        return _userService.updatePassword(
            UUID.fromString(userId),
            updatePasswordRequest.password
        ).toUserResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "400", description = "Invalid password"),
        APIResponse(responseCode = "404", description = "User with sent id does not exist")
    )
    @PUT
    @Path("/makeAdmin/{login}")
    @RolesAllowed("User", "HR", "Admin")
    fun makeUserAdmin(login: String): UserResponse {
        return _userService.makeUserAdmin(login).toUserResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "User with sent id does not exist")
    )
    @DELETE
    @Path("/{id}")
    @RolesAllowed("User", "HR", "Admin")
    fun removeById(): UserResponse {
        return _userService.removeById(UUID.fromString(userId)).toUserResponse()
    }
}