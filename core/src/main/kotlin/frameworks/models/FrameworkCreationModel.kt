package frameworks.models

import java.util.*

data class FrameworkCreationModel(
    var name: String,
) {
    fun toFramework(): Framework {
        return Framework(
            id = UUID.randomUUID(),
            name = name
        )
    }
}