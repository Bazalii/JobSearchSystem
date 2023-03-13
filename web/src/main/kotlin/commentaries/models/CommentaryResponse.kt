package commentaries.models

import java.util.*

data class CommentaryResponse(
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
    var title: String = "",
    var body: String = "",
    var userId: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
)
