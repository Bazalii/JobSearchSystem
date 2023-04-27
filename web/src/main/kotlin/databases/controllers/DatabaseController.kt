package databases.controllers

import databases.extensions.toDatabaseResponse
import databases.models.DatabaseCreationRequest
import databases.models.DatabaseResponse
import databases.services.IDatabaseService
import jakarta.annotation.security.RolesAllowed
import jakarta.enterprise.context.RequestScoped
import jakarta.ws.rs.*
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import java.util.*

@RequestScoped
@Path("databases")
class DatabaseController(
    private var _databaseService: IDatabaseService,
) {

    @APIResponses(
        APIResponse(responseCode = "200", description = "Database is created"),
        APIResponse(responseCode = "400", description = "Invalid name")
    )
    @POST
    @RolesAllowed("Admin")
    fun add(databaseCreationRequest: DatabaseCreationRequest): DatabaseResponse {
        val creationModel = databaseCreationRequest.toCreationModel()

        return _databaseService.add(creationModel).toDatabaseResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @RolesAllowed("User", "HR", "Admin")
    fun getAll(): List<DatabaseResponse> {
        return _databaseService.getAll().map { it.toDatabaseResponse() }
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Database with sent id does not exist")
    )
    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    fun removeById(@PathParam("id") id: UUID): DatabaseResponse {
        return _databaseService.removeById(id).toDatabaseResponse()
    }
}