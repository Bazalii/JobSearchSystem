package commentaries.models

import java.time.LocalDateTime
import java.util.*

data class CommentaryResponse(
    var id: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
    var title: String = "",
    var body: String = "",
    var creationTime: LocalDateTime = LocalDateTime.MIN,
    var userId: UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
)
