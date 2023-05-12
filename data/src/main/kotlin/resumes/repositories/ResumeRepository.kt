package resumes.repositories

import databases.extensions.toDbModel
import frameworks.extensions.toDbModel
import jakarta.enterprise.context.ApplicationScoped
import jakarta.transaction.Transactional
import programmingLanguages.extensions.toDbModel
import resumes.extensions.toResume
import resumes.models.Resume
import resumes.models.ResumeDbModel
import users.repositories.PanacheUserRepository
import java.util.*

@ApplicationScoped
class ResumeRepository(
    private var _panacheUserRepository: PanacheUserRepository,
    private var _panacheResumeRepository: PanacheResumeRepository,
) : IResumeRepository {

    @Transactional
    override fun add(resume: Resume): Resume {
        return _panacheResumeRepository.add(
            ResumeDbModel(
                id = resume.id,
                name = resume.name,
                currentJob = resume.currentJob,
                quote = resume.quote,
                languages = resume.languages.map { it.toDbModel() }.toMutableSet(),
                frameworks = resume.frameworks.map { it.toDbModel() }.toMutableSet(),
                databases = resume.databases.map { it.toDbModel() }.toMutableSet(),
                otherTechnologies = resume.otherTechnologies,
                additionalInformation = resume.additionalInformation,
                user = _panacheUserRepository.getById(resume.userId)
            )
        ).toResume()
    }

    override fun getById(id: UUID): Resume {
        val dbModel = _panacheResumeRepository.getById(id)

        return dbModel.toResume()
    }

    override fun getByUserId(id: UUID): Resume {
        val dbModel = _panacheResumeRepository.getByUserId(id)

        return dbModel.toResume()
    }

    override fun getPage(pageIndex: Int, pageSize: Int): List<Resume> {
        val dbModels = _panacheResumeRepository.getPage(pageIndex, pageSize)

        return dbModels.map { it.toResume() }
    }

    override fun countAll(): Long {
        return _panacheResumeRepository.countAll()
    }

    @Transactional
    override fun update(resume: Resume): Resume {
        return _panacheResumeRepository.update(
            ResumeDbModel(
                id = resume.id,
                name = resume.name,
                currentJob = resume.currentJob,
                quote = resume.quote,
                languages = resume.languages.map { it.toDbModel() }.toMutableSet(),
                frameworks = resume.frameworks.map { it.toDbModel() }.toMutableSet(),
                databases = resume.databases.map { it.toDbModel() }.toMutableSet(),
                otherTechnologies = resume.otherTechnologies,
                additionalInformation = resume.additionalInformation,
                user = _panacheUserRepository.getById(resume.userId)
            )
        ).toResume()
    }

    @Transactional
    override fun removeById(id: UUID): Resume {
        return _panacheResumeRepository.removeById(id).toResume()
    }
}