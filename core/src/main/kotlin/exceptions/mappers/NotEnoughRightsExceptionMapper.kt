package exceptions.mappers

import exceptions.NotEnoughRightsException
import javax.json.Json
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class NotEnoughRightsExceptionMapper : ExceptionMapper<NotEnoughRightsException> {

    override fun toResponse(exception: NotEnoughRightsException?): Response {
        return Response.status(403).entity(Json.createValue(exception?.message)).build()
    }
}