package exceptions.mappers

import exceptions.EntityAlreadyExistsException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class EntityAlreadyExistsExceptionMapper : ExceptionMapper<EntityAlreadyExistsException> {

    override fun toResponse(exception: EntityAlreadyExistsException?): Response {
        return Response.status(409).entity(exception?.message).build()
    }
}