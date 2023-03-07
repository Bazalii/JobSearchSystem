package databases.extensions

import databases.models.Database
import databases.models.DatabaseDbModel

fun Database.toDbModel() =
    DatabaseDbModel(
        id = id,
        name = name,
        resumes = mutableSetOf()
    )