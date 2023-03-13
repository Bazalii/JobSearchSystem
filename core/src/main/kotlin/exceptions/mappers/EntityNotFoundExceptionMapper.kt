package exceptions.mappers

import exceptions.EntityNotFoundException
import javax.json.Json
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class EntityNotFoundExceptionMapper : ExceptionMapper<EntityNotFoundException> {

    override fun toResponse(exception: EntityNotFoundException?): Response {
        return Response.status(404).entity(Json.createValue(exception?.message)).build()
    }
}