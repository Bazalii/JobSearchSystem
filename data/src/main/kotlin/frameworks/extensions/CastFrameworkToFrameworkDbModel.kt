package frameworks.extensions

import frameworks.models.Framework
import frameworks.models.FrameworkDbModel

fun Framework.toDbModel() =
    FrameworkDbModel(
        id = id,
        name = name,
        resumes = mutableSetOf()
    )