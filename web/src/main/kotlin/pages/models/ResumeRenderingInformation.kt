package pages.models

import resumes.models.Resume

data class ResumeRenderingInformation(
    val resume: Resume,
    val creatorEmail: String,
)
