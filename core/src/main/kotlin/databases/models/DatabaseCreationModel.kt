package databases.models

import java.util.*

data class DatabaseCreationModel(
    var name: String,
) {
    fun toDatabase(): Database {
        return Database(
            id = UUID.randomUUID(),
            name = name
        )
    }
}

