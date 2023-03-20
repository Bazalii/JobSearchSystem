package commentaries.controllers

import commentaries.extensions.toResponseCommentary
import commentaries.models.CommentaryCreationRequest
import commentaries.models.CommentaryResponse
import commentaries.services.ICommentaryService
import exceptions.NotEnoughRightsException
import org.eclipse.microprofile.jwt.Claim
import org.eclipse.microprofile.jwt.Claims
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import java.util.*
import javax.annotation.security.RolesAllowed
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path

@RequestScoped
@Path("commentaries")
class CommentaryController(
    private var _commentaryService: ICommentaryService,
) {

    @Inject
    @Claim(standard = Claims.upn)
    private lateinit var userId: String

    @Inject
    @Claim(standard = Claims.groups)
    private lateinit var groups: Set<String>

    @APIResponses(
        APIResponse(responseCode = "200", description = "Commentary is created"),
        APIResponse(responseCode = "400", description = "Invalid title or body"),
        APIResponse(responseCode = "404", description = "User with sent id does not exist")
    )
    @POST
    @RolesAllowed("User", "HR", "Admin")
    fun add(commentaryCreationRequest: CommentaryCreationRequest): CommentaryResponse {
        val creationModel = commentaryCreationRequest.toCreationModel()

        return _commentaryService.add(creationModel).toResponseCommentary()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Commentary with sent id does not exist")
    )
    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    fun getById(id: UUID): CommentaryResponse {
        val commentary = _commentaryService.getById(id)

        return commentary.toResponseCommentary()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("/user/{userId}")
    @RolesAllowed("Admin")
    fun getAllByUserId(userId: UUID): List<CommentaryResponse> {
        val commentaries = _commentaryService.getAllByUserId(userId)

        return commentaries.map { it.toResponseCommentary() }
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Commentary with sent id does not exist")
    )
    @DELETE
    @Path("/{id}")
    @RolesAllowed("User", "HR", "Admin")
    fun removeById(id: UUID): CommentaryResponse {
        val commentary = _commentaryService.getById(id)

        if (!groups.contains("Admin") && commentary.userId != UUID.fromString(userId)) {
            throw NotEnoughRightsException("You do not have access to this commentary!")
        }

        return _commentaryService.removeById(id).toResponseCommentary()
    }
}