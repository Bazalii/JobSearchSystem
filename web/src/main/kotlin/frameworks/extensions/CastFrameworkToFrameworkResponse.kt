package frameworks.extensions

import frameworks.models.Framework
import frameworks.models.FrameworkResponse

fun Framework.toFrameworkResponse() =
    FrameworkResponse(
        id = id,
        name = name
    )