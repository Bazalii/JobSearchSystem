package programmingLanguages.extensions

import programmingLanguages.models.ProgrammingLanguage
import programmingLanguages.models.ProgrammingLanguageResponse

fun ProgrammingLanguage.toProgrammingLanguageResponse() =
    ProgrammingLanguageResponse(
        id = id,
        name = name
    )