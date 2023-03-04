package frameworks.extensions

import frameworks.models.Framework
import frameworks.models.FrameworkDbModel

fun FrameworkDbModel.toFramework() =
    Framework(
        id = id,
        name = name
    )