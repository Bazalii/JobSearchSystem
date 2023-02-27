package exceptions.mappers

import exceptions.InvalidEntityException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class InvalidEntityExceptionMapper : ExceptionMapper<InvalidEntityException> {

    override fun toResponse(exception: InvalidEntityException?): Response {
        return Response.status(400).entity(exception?.message).build()
    }
}