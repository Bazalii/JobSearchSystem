package exceptions.mappers

import exceptions.EntityNotFoundException
import jakarta.json.Json
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class EntityNotFoundExceptionMapper : ExceptionMapper<EntityNotFoundException> {

    override fun toResponse(exception: EntityNotFoundException?): Response {
        return Response.status(404).entity(Json.createValue(exception?.message)).build()
    }
}