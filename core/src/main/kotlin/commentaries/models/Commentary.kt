package commentaries.models

import java.time.LocalDateTime
import java.util.*

data class Commentary(
    var id: UUID,
    var title: String,
    var body: String,
    var creationTime: LocalDateTime,
    var userId: UUID,
)