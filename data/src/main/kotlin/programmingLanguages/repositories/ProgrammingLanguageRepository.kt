package programmingLanguages.repositories

import programmingLanguages.extensions.toDbModel
import programmingLanguages.extensions.toProgrammingLanguage
import programmingLanguages.models.ProgrammingLanguage
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProgrammingLanguageRepository(
    private var _panacheProgrammingLanguageRepository: PanacheProgrammingLanguageRepository,
) : IProgrammingLanguageRepository {

    override fun add(programmingLanguage: ProgrammingLanguage): ProgrammingLanguage {
        return _panacheProgrammingLanguageRepository.add(programmingLanguage.toDbModel()).toProgrammingLanguage()
    }

    override fun getById(id: UUID): ProgrammingLanguage {
        return _panacheProgrammingLanguageRepository.getById(id).toProgrammingLanguage()
    }

    override fun getAll(): List<ProgrammingLanguage> {
        return _panacheProgrammingLanguageRepository.getAll().map { it.toProgrammingLanguage() }
    }

    override fun removeById(id: UUID): ProgrammingLanguage {
        return _panacheProgrammingLanguageRepository.removeById(id).toProgrammingLanguage()
    }
}