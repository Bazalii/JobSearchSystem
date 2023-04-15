package programmingLanguages.controllers

import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import programmingLanguages.extensions.toProgrammingLanguageResponse
import programmingLanguages.models.ProgrammingLanguageCreationRequest
import programmingLanguages.models.ProgrammingLanguageResponse
import programmingLanguages.services.IProgrammingLanguageService
import java.util.*
import javax.annotation.security.RolesAllowed
import javax.enterprise.context.RequestScoped
import javax.ws.rs.*

@RequestScoped
@Path("programmingLanguages")
class ProgrammingLanguageController(
    private var _programmingLanguageService: IProgrammingLanguageService,
) {

    @APIResponses(
        APIResponse(responseCode = "200", description = "Programming language is created"),
        APIResponse(responseCode = "400", description = "Invalid name")
    )
    @POST
    @RolesAllowed("Admin")
    fun add(programmingLanguageCreationRequest: ProgrammingLanguageCreationRequest): ProgrammingLanguageResponse {
        val creationModel = programmingLanguageCreationRequest.toCreationModel()

        return _programmingLanguageService.add(creationModel).toProgrammingLanguageResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @RolesAllowed("User", "HR", "Admin")
    fun getAll(): List<ProgrammingLanguageResponse> {
        return _programmingLanguageService.getAll().map { it.toProgrammingLanguageResponse() }
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Programming language with sent id does not exist")
    )
    @DELETE
    @Path("/{id}")
    @RolesAllowed("Admin")
    fun removeById(@PathParam("id") id: UUID): ProgrammingLanguageResponse {
        return _programmingLanguageService.removeById(id).toProgrammingLanguageResponse()
    }
}