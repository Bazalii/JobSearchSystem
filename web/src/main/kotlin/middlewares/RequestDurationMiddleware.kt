package middlewares

import org.jboss.resteasy.reactive.server.ServerRequestFilter
import org.jboss.resteasy.reactive.server.ServerResponseFilter
import java.time.Duration
import java.time.LocalDateTime
import javax.enterprise.context.RequestScoped
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerResponseContext

@RequestScoped
class RequestDurationMiddleware {
    private var _requestStartTime = LocalDateTime.MIN
    private val _timeSectionName = "Время загрузки страницы: "

    @ServerRequestFilter
    fun startRequestDurationProcessing(requestContext: ContainerRequestContext) {
        if (requestContext.uriInfo.requestUri.toString().contains("pages")) {
            _requestStartTime = LocalDateTime.now()
        }
    }

    @ServerResponseFilter
    fun endRequestDurationProcessing(responseContext: ContainerResponseContext) {
        if (_requestStartTime != LocalDateTime.MIN) {
            val requestDuration = Duration.between(_requestStartTime, LocalDateTime.now()).toNanos()

            val entity = responseContext.entity.toString()

            val indexOfTimeSection = entity.indexOf(_timeSectionName)

            responseContext.entity = entity.substring(0, indexOfTimeSection + _timeSectionName.length) +
                    requestDuration.toString() + " nanoseconds(server) + " + entity.substring(indexOfTimeSection + _timeSectionName.length)
        }
    }
}