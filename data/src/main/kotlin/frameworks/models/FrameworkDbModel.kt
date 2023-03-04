package frameworks.models

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "\"frameworks\"")
data class FrameworkDbModel(
    @Id
    var id: UUID,
    var name: String,
)