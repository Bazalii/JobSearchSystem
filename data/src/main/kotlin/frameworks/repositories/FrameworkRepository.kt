package frameworks.repositories

import frameworks.extensions.toDbModel
import frameworks.extensions.toFramework
import frameworks.models.Framework
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import java.util.*

@ApplicationScoped
class FrameworkRepository(
    private var _panacheFrameworkRepository: PanacheFrameworkRepository,
) : IFrameworkRepository {

    @Transactional
    override fun add(framework: Framework): Framework {
        return _panacheFrameworkRepository.add(framework.toDbModel()).toFramework()
    }

    override fun getById(id: UUID): Framework {
        return _panacheFrameworkRepository.getById(id).toFramework()
    }

    override fun getAll(): List<Framework> {
        return _panacheFrameworkRepository.getAll().map { it.toFramework() }
    }

    @Transactional
    override fun removeById(id: UUID): Framework {
        return _panacheFrameworkRepository.removeById(id).toFramework()
    }
}