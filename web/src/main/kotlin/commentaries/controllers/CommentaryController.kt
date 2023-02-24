package commentaries.controllers

import commentaries.extensions.toResponseCommentary
import commentaries.models.CommentaryCreationModel
import commentaries.models.CommentaryCreationRequest
import commentaries.models.CommentaryResponse
import commentaries.services.ICommentaryService
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

    @POST
    fun add(commentaryCreationRequest: CommentaryCreationRequest) {
        _commentaryService.add(
            CommentaryCreationModel(
                title = commentaryCreationRequest.title,
                body = commentaryCreationRequest.body,
                userId = commentaryCreationRequest.userId
            )
        )
    }

    @GET
    @Path("/{id}")
    fun getById(id: UUID): CommentaryResponse {
        val commentary = _commentaryService.getById(id)

        return commentary.toResponseCommentary()
    }

    @GET
    @Path("/{userId}")
    fun getAllByUserId(userId: UUID): List<CommentaryResponse> {
        val commentaries = _commentaryService.getAllByUserId(userId)

        return commentaries.map { it.toResponseCommentary() }
    }

    @DELETE
    @Path("/{id}")
    fun removeById(id: UUID) {
        _commentaryService.removeById(id)
    }
}