package resumes.services.implementations

import commonClasses.IThrowingValidator
import databases.services.IDatabaseService
import frameworks.services.IFrameworkService
import jakarta.enterprise.context.ApplicationScoped
import programmingLanguages.services.IProgrammingLanguageService
import resumes.models.Resume
import resumes.models.ResumeCreationModel
import resumes.models.ResumeUpdateModel
import resumes.repositories.IResumeRepository
import resumes.services.IResumeService
import java.util.*

@ApplicationScoped
class ResumeService(
    private var _programmingLanguageService: IProgrammingLanguageService,
    private var _frameworkService: IFrameworkService,
    private var _databaseService: IDatabaseService,
    private var _resumeRepository: IResumeRepository,
    private var _resumeValidator: IThrowingValidator<Resume>,
) : IResumeService {

    override fun add(resumeCreationModel: ResumeCreationModel): Resume {
        val resume = Resume(
            id = UUID.randomUUID(),
            name = resumeCreationModel.name,
            currentJob = resumeCreationModel.currentJob,
            quote = resumeCreationModel.quote,
            languages = resumeCreationModel.languages.map { _programmingLanguageService.getById(it) }.toMutableSet(),
            frameworks = resumeCreationModel.frameworks.map { _frameworkService.getById(it) }.toMutableSet(),
            databases = resumeCreationModel.databases.map { _databaseService.getById(it) }.toMutableSet(),
            otherTechnologies = resumeCreationModel.otherTechnologies,
            additionalInformation = resumeCreationModel.additionalInformation,
            userId = resumeCreationModel.userId
        )

        _resumeValidator.validate(resume)

        return _resumeRepository.add(resume)
    }

    override fun getById(id: UUID): Resume {
        return _resumeRepository.getById(id)
    }

    override fun getByUserId(id: UUID): Resume {
        return _resumeRepository.getByUserId(id)
    }

    override fun getPage(pageIndex: Int, pageSize: Int): List<Resume> {
        return _resumeRepository.getPage(pageIndex, pageSize)
    }

    override fun countAll(): Long {
        return _resumeRepository.countAll()
    }

    override fun update(resumeUpdateModel: ResumeUpdateModel): Resume {
        val foundResume = _resumeRepository.getById(resumeUpdateModel.id)

        val updatedResume = Resume(
            id = resumeUpdateModel.id,
            name = resumeUpdateModel.name,
            currentJob = resumeUpdateModel.currentJob,
            quote = resumeUpdateModel.quote,
            languages = resumeUpdateModel.languages.map { _programmingLanguageService.getById(it) }.toMutableSet(),
            frameworks = resumeUpdateModel.frameworks.map { _frameworkService.getById(it) }.toMutableSet(),
            databases = resumeUpdateModel.databases.map { _databaseService.getById(it) }.toMutableSet(),
            otherTechnologies = resumeUpdateModel.otherTechnologies,
            additionalInformation = resumeUpdateModel.additionalInformation,
            projects = foundResume.projects,
            workExperiences = foundResume.workExperiences,
            userId = resumeUpdateModel.userId
        )

        _resumeValidator.validate(updatedResume)

        return _resumeRepository.update(updatedResume)
    }

    override fun removeById(id: UUID): Resume {
        return _resumeRepository.removeById(id)
    }
}