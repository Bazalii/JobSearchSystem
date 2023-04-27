package middlewares

import java.net.URI
import javax.annotation.Priority
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.core.Response
import javax.ws.rs.ext.Provider

@Provider
@Priority(1000)
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