package commentaries.controllers

import commentaries.extensions.toResponseCommentary
import commentaries.models.CommentaryCreationModel
import commentaries.models.CommentaryCreationRequest
import commentaries.models.CommentaryResponse
import commentaries.services.ICommentaryService
import exceptions.NotEnoughRightsException
import org.eclipse.microprofile.jwt.Claim
import org.eclipse.microprofile.jwt.Claims
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import updatesNotifications.notificators.IUpdatesNotificator
import java.util.*
import javax.annotation.security.RolesAllowed
import javax.enterprise.context.RequestScoped
import javax.inject.Inject
import javax.ws.rs.*

@RequestScoped
@Path("commentaries")
class CommentaryController(
    private var _commentaryService: ICommentaryService,
    private val _updatesNotificator: IUpdatesNotificator,
) {

    @Inject
    @Claim(standard = Claims.groups)
    private lateinit var _groups: Set<String>
    private lateinit var _userId: UUID

    @Inject
    private fun init(@Claim(standard = Claims.upn) userIdString: String?) {
        if (userIdString != null) {
            _userId = UUID.fromString(userIdString)
        }
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Commentary is created"),
        APIResponse(responseCode = "400", description = "Invalid title or body"),
        APIResponse(responseCode = "404", description = "User with sent id does not exist")
    )
    @POST
    @RolesAllowed("User", "HR", "Admin")
    fun add(commentaryCreationRequest: CommentaryCreationRequest): CommentaryResponse {
        val creationModel = CommentaryCreationModel(
            title = commentaryCreationRequest.title,
            body = commentaryCreationRequest.body,
            userId = _userId
        )

        val commentaryResponse = _commentaryService.add(creationModel).toResponseCommentary()

        _updatesNotificator.notifyAll("Commentary is added!")

        return commentaryResponse
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Commentary with sent id does not exist")
    )
    @GET
    @Path("/{id}")
    @RolesAllowed("Admin")
    fun getById(@PathParam("id") id: UUID): CommentaryResponse {
        val commentary = _commentaryService.getById(id)

        return commentary.toResponseCommentary()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("/user/{userId}")
    @RolesAllowed("Admin")
    fun getAllByUserId(@PathParam("userId") userId: UUID): List<CommentaryResponse> {
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
    fun removeById(@PathParam("id") id: UUID): CommentaryResponse {
        val commentary = _commentaryService.getById(id)

        if (!_groups.contains("Admin") && commentary.userId != _userId) {
            throw NotEnoughRightsException("You do not have access to this commentary!")
        }

        val commentaryResponse = _commentaryService.removeById(id).toResponseCommentary()

        _updatesNotificator.notifyAll("Commentary is deleted!")

        return commentaryResponse
    }
}