package commentaries.models

import java.util.*

data class CommentaryCreationRequest(
    var title: String = "",
    var body: String = "",
    var userId: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
){
    fun toCreationModel() = CommentaryCreationModel(
        title = title,
        body = body,
        userId = userId
    )
}
