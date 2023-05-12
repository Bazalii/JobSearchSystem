package middlewares

import jakarta.ws.rs.container.ContainerRequestContext
import jakarta.ws.rs.container.ContainerRequestFilter
import jakarta.ws.rs.container.PreMatching
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.Provider
import java.net.URI

@Provider
@PreMatching
class LoginRedirectionMiddleware : ContainerRequestFilter {

    override fun filter(requestContext: ContainerRequestContext) {
        val requestUri = requestContext.uriInfo.requestUri.toString()
        val wantedPage = "/pages/" + requestUri.substringAfter("pages/")

        if (requestUri.contains("pages") &&
            wantedPage != "/pages/login" &&
            wantedPage != "/pages/registration" &&
            !requestContext.headers.containsKey("Authorization")
        ) {
            requestContext.abortWith(
                Response.seeOther(URI.create("http://localhost:8080/pages/login#${wantedPage}")).build()
            )
        }
    }
}