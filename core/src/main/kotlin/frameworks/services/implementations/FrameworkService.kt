package frameworks.services.implementations

import commonClasses.IThrowingValidator
import frameworks.models.Framework
import frameworks.models.FrameworkCreationModel
import frameworks.repositories.IFrameworkRepository
import frameworks.services.IFrameworkService
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class FrameworkService(
    private var _frameworkRepository: IFrameworkRepository,
    private var _frameworkValidator: IThrowingValidator<Framework>,
) : IFrameworkService {
    override fun add(frameworkCreationModel: FrameworkCreationModel): Framework {
        val framework = frameworkCreationModel.toFramework()

        _frameworkValidator.validate(framework)

        return _frameworkRepository.add(framework)
    }

    override fun getById(id: UUID): Framework {
        return _frameworkRepository.getById(id)
    }

    override fun getAll(): List<Framework> {
        return _frameworkRepository.getAll()
    }

    override fun removeById(id: UUID): Framework {
        return _frameworkRepository.removeById(id)
    }
}