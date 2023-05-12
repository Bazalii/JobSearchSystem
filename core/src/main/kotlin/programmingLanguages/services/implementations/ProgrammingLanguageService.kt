package programmingLanguages.services.implementations

import commonClasses.IThrowingValidator
import jakarta.enterprise.context.ApplicationScoped
import programmingLanguages.models.ProgrammingLanguage
import programmingLanguages.models.ProgrammingLanguageCreationModel
import programmingLanguages.repositories.IProgrammingLanguageRepository
import programmingLanguages.services.IProgrammingLanguageService
import java.util.*

@ApplicationScoped
class ProgrammingLanguageService(
    private var _programmingLanguageRepository: IProgrammingLanguageRepository,
    private var _programmingLanguageValidator: IThrowingValidator<ProgrammingLanguage>,
) : IProgrammingLanguageService {
    override fun add(programmingLanguageCreationModel: ProgrammingLanguageCreationModel): ProgrammingLanguage {
        val programmingLanguage = programmingLanguageCreationModel.toProgrammingLanguage()

        _programmingLanguageValidator.validate(programmingLanguage)

        return _programmingLanguageRepository.add(programmingLanguage)
    }

    override fun getById(id: UUID): ProgrammingLanguage {
        return _programmingLanguageRepository.getById(id)
    }

    override fun getAll(): List<ProgrammingLanguage> {
        return _programmingLanguageRepository.getAll()
    }

    override fun removeById(id: UUID): ProgrammingLanguage {
        return _programmingLanguageRepository.removeById(id)
    }
}