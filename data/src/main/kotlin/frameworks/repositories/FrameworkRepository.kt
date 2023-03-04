package frameworks.repositories

import frameworks.extensions.toFramework
import frameworks.models.Framework
import frameworks.models.FrameworkDbModel
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class FrameworkRepository(
    private var _panacheFrameworkRepository: PanacheFrameworkRepository,
) : IFrameworkRepository {

    @Transactional
    override fun add(framework: Framework): Framework {
        return _panacheFrameworkRepository.add(
            FrameworkDbModel(
                id = framework.id,
                name = framework.name
            )
        ).toFramework()
    }

    override fun getAll(): List<Framework> {
        return _panacheFrameworkRepository.getAll().map { it.toFramework() }
    }

    @Transactional
    override fun removeById(id: UUID): Framework {
        return _panacheFrameworkRepository.removeById(id).toFramework()
    }
}