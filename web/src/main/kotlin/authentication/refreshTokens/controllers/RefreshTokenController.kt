package authentication.refreshTokens.controllers

import authentication.refreshTokens.models.UserCredentials
import authentication.refreshTokens.services.IRefreshTokenService
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import javax.enterprise.context.RequestScoped
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

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