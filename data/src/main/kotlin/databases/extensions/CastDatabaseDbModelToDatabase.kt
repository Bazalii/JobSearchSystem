package databases.extensions

import databases.models.Database
import databases.models.DatabaseDbModel

fun DatabaseDbModel.toDatabase() =
    Database(
        id = id,
        name = name
    )