package programmingLanguages.services

import programmingLanguages.models.ProgrammingLanguage
import programmingLanguages.models.ProgrammingLanguageCreationModel
import java.util.*

interface IProgrammingLanguageService {
    fun add(programmingLanguageCreationModel: ProgrammingLanguageCreationModel): ProgrammingLanguage
    fun getAll(): List<ProgrammingLanguage>
    fun removeById(id: UUID): ProgrammingLanguage
}