package databases.extensions

import databases.models.Database
import databases.models.DatabaseResponse

fun Database.toDatabaseResponse() =
    DatabaseResponse(
        id = id,
        name = name
    )