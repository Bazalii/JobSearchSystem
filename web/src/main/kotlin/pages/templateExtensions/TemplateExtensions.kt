package pages.templateExtensions

import io.quarkus.qute.TemplateExtension
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset

object TemplateExtensions {

    @TemplateExtension
    @JvmStatic
    fun getPastTime(localDateTime: LocalDateTime): Long {
        return Duration.between(localDateTime, LocalDateTime.now(ZoneOffset.UTC)).toMillis()
    }
}