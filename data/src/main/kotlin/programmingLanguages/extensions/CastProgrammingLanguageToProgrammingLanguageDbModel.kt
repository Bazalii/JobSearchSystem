package programmingLanguages.extensions

import programmingLanguages.models.ProgrammingLanguage
import programmingLanguages.models.ProgrammingLanguageDbModel

fun ProgrammingLanguage.toDbModel() =
    ProgrammingLanguageDbModel(
        id = id,
        name = name,
        resumes = mutableSetOf()
    )