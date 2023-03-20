package exceptions.mappers

import exceptions.InvalidCredentialsException
import javax.json.Json
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class InvalidCredentialsExceptionMapper : ExceptionMapper<InvalidCredentialsException> {

    override fun toResponse(exception: InvalidCredentialsException?): Response {
        return Response.status(404).entity(Json.createValue(exception?.message)).build()
    }
}