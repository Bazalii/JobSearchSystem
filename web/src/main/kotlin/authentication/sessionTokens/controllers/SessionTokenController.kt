package authentication.sessionTokens.controllers

import authentication.sessionTokens.services.ISessionTokenService
import jakarta.annotation.security.PermitAll
import jakarta.enterprise.context.RequestScoped
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.jwt.JsonWebToken
import java.util.*

@RequestScoped
@Path("sessionTokens")
class SessionTokenController(
    private var _sessionTokenService: ISessionTokenService,
    private var jsonWebToken: JsonWebToken,
) {

    @GET
    @Path("/refresh")
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    fun refresh(): String {
        return _sessionTokenService.refresh(
            UUID.fromString(jsonWebToken.getClaim("upn"))
        )
    }
}