package authentication.sessionTokens.controllers

import authentication.sessionTokens.services.ISessionTokenService
import org.eclipse.microprofile.jwt.JsonWebToken
import java.util.*
import javax.annotation.security.PermitAll
import javax.enterprise.context.RequestScoped
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

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