package exceptions.mappers

import exceptions.InvalidCredentialsException
import jakarta.json.Json
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class InvalidCredentialsExceptionMapper : ExceptionMapper<InvalidCredentialsException> {

    override fun toResponse(exception: InvalidCredentialsException?): Response {
        return Response.status(404).entity(Json.createValue(exception?.message)).build()
    }
}