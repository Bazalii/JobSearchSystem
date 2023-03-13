package programmingLanguages.extensions

import programmingLanguages.models.ProgrammingLanguage
import programmingLanguages.models.ProgrammingLanguageDbModel

fun ProgrammingLanguageDbModel.toProgrammingLanguage() =
    ProgrammingLanguage(
        id = id,
        name = name
    )