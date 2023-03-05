package databases.controllers

import databases.extensions.toDatabaseResponse
import databases.models.DatabaseCreationRequest
import databases.models.DatabaseResponse
import databases.services.IDatabaseService
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import java.util.*
import javax.enterprise.context.RequestScoped
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path

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
    fun add(databaseCreationRequest: DatabaseCreationRequest): DatabaseResponse {
        val creationModel = databaseCreationRequest.toCreationModel()

        return _databaseService.add(creationModel).toDatabaseResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    fun getAll(): List<DatabaseResponse> {
        return _databaseService.getAll().map { it.toDatabaseResponse() }
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Database with sent id does not exist")
    )
    @DELETE
    @Path("/{id}")
    fun removeById(id: UUID): DatabaseResponse {
        return _databaseService.removeById(id).toDatabaseResponse()
    }
}