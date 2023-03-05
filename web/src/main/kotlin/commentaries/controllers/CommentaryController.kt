package commentaries.controllers

import commentaries.extensions.toResponseCommentary
import commentaries.models.CommentaryCreationRequest
import commentaries.models.CommentaryResponse
import commentaries.services.ICommentaryService
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import java.util.*
import javax.enterprise.context.RequestScoped
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path

@RequestScoped
@Path("commentaries")
class CommentaryController(
    private var _commentaryService: ICommentaryService,
) {

    @APIResponses(
        APIResponse(responseCode = "200", description = "Commentary is created"),
        APIResponse(responseCode = "400", description = "Invalid title or body")
    )
    @POST
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
    fun getById(id: UUID): CommentaryResponse {
        val commentary = _commentaryService.getById(id)

        return commentary.toResponseCommentary()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("/{userId}")
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
    fun removeById(id: UUID): CommentaryResponse {
        return _commentaryService.removeById(id).toResponseCommentary()
    }
}