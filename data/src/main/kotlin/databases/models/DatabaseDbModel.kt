package databases.models

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "\"databases\"")
data class DatabaseDbModel(
    @Id
    var id: UUID,
    var name: String
)