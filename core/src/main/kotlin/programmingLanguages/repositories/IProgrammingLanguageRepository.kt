package programmingLanguages.repositories

import programmingLanguages.models.ProgrammingLanguage
import java.util.*

interface IProgrammingLanguageRepository {
    fun add(programmingLanguage: ProgrammingLanguage): ProgrammingLanguage
    fun getAll(): List<ProgrammingLanguage>
    fun removeById(id: UUID): ProgrammingLanguage
}