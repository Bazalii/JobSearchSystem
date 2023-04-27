package authentication.refreshTokens.controllers

import authentication.refreshTokens.models.UserCredentials
import authentication.refreshTokens.services.IRefreshTokenService
import jakarta.enterprise.context.RequestScoped
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses

@RequestScoped
@Path("refreshTokens")
class RefreshTokenController(
    private var _refreshTokenService: IRefreshTokenService,
) {

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Invalid information")
    )
    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    fun login(credentials: UserCredentials): String {
        return _refreshTokenService.login(credentials)
    }
}