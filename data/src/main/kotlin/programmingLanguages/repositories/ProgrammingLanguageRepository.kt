package programmingLanguages.repositories

import programmingLanguages.extensions.toProgrammingLanguage
import programmingLanguages.models.ProgrammingLanguage
import programmingLanguages.models.ProgrammingLanguageDbModel
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ProgrammingLanguageRepository(
    private var _panacheProgrammingLanguageRepository: PanacheProgrammingLanguageRepository,
) : IProgrammingLanguageRepository {

    override fun add(programmingLanguage: ProgrammingLanguage): ProgrammingLanguage {
        return _panacheProgrammingLanguageRepository.add(
            ProgrammingLanguageDbModel(
                id = programmingLanguage.id,
                name = programmingLanguage.name
            )
        ).toProgrammingLanguage()
    }

    override fun getAll(): List<ProgrammingLanguage> {
        return _panacheProgrammingLanguageRepository.getAll().map { it.toProgrammingLanguage() }
    }

    override fun removeById(id: UUID): ProgrammingLanguage {
        return _panacheProgrammingLanguageRepository.removeById(id).toProgrammingLanguage()
    }
}