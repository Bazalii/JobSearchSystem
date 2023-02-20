package resumes.services.implementations

import resumes.models.Resume
import resumes.models.ResumeCreationModel
import resumes.repositories.IResumeRepository
import resumes.services.IResumeService
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ResumeService(
    private var _resumeRepository: IResumeRepository
) : IResumeService {

    override fun add(resumeCreationModel: ResumeCreationModel) {
        _resumeRepository.add(
            Resume(
                id = UUID.randomUUID(),
                name = resumeCreationModel.name,
                currentJob = resumeCreationModel.currentJob,
                quote = resumeCreationModel.quote,
                languages = resumeCreationModel.languages,
                frameworks = resumeCreationModel.frameworks,
                databases = resumeCreationModel.databases,
                otherTechnologies = resumeCreationModel.otherTechnologies,
                additionalInformation = resumeCreationModel.additionalInformation,
                userId = resumeCreationModel.userId
            )
        )
    }

    override fun getById(id: UUID): Resume {
        return _resumeRepository.getById(id)
    }

    override fun getAllByUserId(id: UUID): List<Resume> {
        return _resumeRepository.getAllByUserId(id)
    }

    override fun update(resume: Resume) {
        _resumeRepository.update(resume)
    }

    override fun removeById(id: UUID) {
        _resumeRepository.removeById(id)
    }
}