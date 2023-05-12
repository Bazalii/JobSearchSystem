package exceptions.mappers

import exceptions.InvalidEntityException
import jakarta.json.Json
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class InvalidEntityExceptionMapper : ExceptionMapper<InvalidEntityException> {

    override fun toResponse(exception: InvalidEntityException?): Response {
        return Response.status(400).entity(Json.createValue(exception?.message)).build()
    }
}