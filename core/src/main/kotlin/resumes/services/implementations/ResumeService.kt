package resumes.services.implementations

import commonClasses.IThrowingValidator
import resumes.models.Resume
import resumes.models.ResumeCreationModel
import resumes.repositories.IResumeRepository
import resumes.services.IResumeService
import java.util.*
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ResumeService(
    private var _resumeRepository: IResumeRepository,
    private var _resumeValidator: IThrowingValidator<Resume>,
) : IResumeService {

    override fun add(resumeCreationModel: ResumeCreationModel): Resume {
        val resume = resumeCreationModel.toResume()

        _resumeValidator.validate(resume)

        return _resumeRepository.add(resume)
    }

    override fun getById(id: UUID): Resume {
        return _resumeRepository.getById(id)
    }

    override fun getAllByUserId(id: UUID): List<Resume> {
        return _resumeRepository.getAllByUserId(id)
    }

    override fun update(resume: Resume): Resume {
        _resumeValidator.validate(resume)

        return _resumeRepository.update(resume)
    }

    override fun removeById(id: UUID): Resume {
        return _resumeRepository.removeById(id)
    }
}