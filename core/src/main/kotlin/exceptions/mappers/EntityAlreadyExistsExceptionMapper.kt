package exceptions.mappers

import exceptions.EntityAlreadyExistsException
import jakarta.json.Json
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class EntityAlreadyExistsExceptionMapper : ExceptionMapper<EntityAlreadyExistsException> {

    override fun toResponse(exception: EntityAlreadyExistsException?): Response {
        return Response.status(409).entity(Json.createValue(exception?.message)).build()
    }
}