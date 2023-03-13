package exceptions.mappers

import exceptions.InvalidEntityException
import javax.json.Json
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class InvalidEntityExceptionMapper : ExceptionMapper<InvalidEntityException> {

    override fun toResponse(exception: InvalidEntityException?): Response {
        return Response.status(400).entity(Json.createValue(exception?.message)).build()
    }
}