package exceptions.mappers

import exceptions.NotEnoughRightsException
import jakarta.json.Json
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class NotEnoughRightsExceptionMapper : ExceptionMapper<NotEnoughRightsException> {

    override fun toResponse(exception: NotEnoughRightsException?): Response {
        return Response.status(403).entity(Json.createValue(exception?.message)).build()
    }
}