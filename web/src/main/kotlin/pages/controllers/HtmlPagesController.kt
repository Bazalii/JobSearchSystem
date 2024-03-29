package pages.controllers

import commentaries.services.ICommentaryService
import databases.services.IDatabaseService
import exceptions.EntityNotFoundException
import frameworks.services.IFrameworkService
import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import jakarta.annotation.security.RolesAllowed
import jakarta.enterprise.context.RequestScoped
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.jwt.Claim
import org.eclipse.microprofile.jwt.Claims
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import pages.models.*
import programmingLanguages.services.IProgrammingLanguageService
import projects.services.IProjectService
import resumes.models.Resume
import resumes.services.IResumeService
import users.services.IUserService
import workExperience.services.IWorkExperienceService
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

@RequestScoped
@Path("pages")
class HtmlPagesController(
    private val _userService: IUserService,
    private val _resumeService: IResumeService,
    private val _programmingLanguageService: IProgrammingLanguageService,
    private val _frameworkService: IFrameworkService,
    private val _databaseService: IDatabaseService,
    private val _projectService: IProjectService,
    private val _commentaryService: ICommentaryService,
    private val _workExperienceService: IWorkExperienceService,
) {

    @Inject
    @Claim(standard = Claims.groups)
    private lateinit var _groups: Set<String>
    private lateinit var _userId: UUID
    private lateinit var _requestStartTime: LocalDateTime

    @Inject
    private fun init(@Claim(standard = Claims.upn) userIdString: String?) {
        if (userIdString != null) {
            _userId = UUID.fromString(userIdString)
        }

        _requestStartTime = LocalDateTime.now(ZoneOffset.UTC)
    }

    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun myResume(resumeRenderingInformation: MyResumeRenderingInformation): TemplateInstance

        @JvmStatic
        external fun resumes(resumesRenderingInformation: ResumesRenderingInformation): TemplateInstance

        @JvmStatic
        external fun admin(adminPageRenderingInformation: AdminPageRenderingInformation): TemplateInstance

        @JvmStatic
        external fun commentaries(commentariesRenderingInformation: CommentariesRenderingInformation): TemplateInstance

        @JvmStatic
        external fun registration(): TemplateInstance

        @JvmStatic
        external fun login(): TemplateInstance
    }


    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("myResume")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed("User")
    fun getMyResumePage(): TemplateInstance {
        val resume = try {
            _resumeService.getByUserId(_userId)
        } catch (exception: EntityNotFoundException) {
            Resume()
        }

        val allLanguages = _programmingLanguageService.getAll()
        val allFrameworks = _frameworkService.getAll()
        val allDatabases = _databaseService.getAll()

        val projects = _projectService.getAllByResumeId(resume.id)
        val workExperienceItems = _workExperienceService.getAllByResumeId(resume.id)

        return Templates.myResume(
            MyResumeRenderingInformation(
                resume,
                allLanguages,
                allFrameworks,
                allDatabases,
                projects,
                workExperienceItems,
                _requestStartTime
            )
        )
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("resumes")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed("User", "HR", "Admin")
    fun getResumesPage(
        @QueryParam("pageIndex") pageIndex: Int,
        @QueryParam("pageSize") pageSize: Int,
    ): TemplateInstance {
        val resumes = _resumeService.getPage(pageIndex, pageSize)
        val resumesNumber = _resumeService.countAll()
        var numberOfPages = (resumesNumber / pageSize).toInt()

        if ((resumesNumber % pageSize).toInt() != 0) {
            numberOfPages += 1
        }

        return Templates.resumes(
            ResumesRenderingInformation(
                resumes.map {
                    ResumeRenderingInformation(it, _userService.getById(it.userId).email)
                },
                pageIndex + 1,
                numberOfPages,
                _groups,
                _userId,
                _requestStartTime
            )
        )
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("commentaries")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed("User", "HR", "Admin")
    fun getCommentariesPage(
        @QueryParam("pageIndex") pageIndex: Int,
        @QueryParam("pageSize") pageSize: Int,
    ): TemplateInstance {
        val commentaries = _commentaryService.getPage(pageIndex, pageSize)
        val commentariesNumber = _commentaryService.countAll()
        var numberOfPages = (commentariesNumber / pageSize).toInt()

        if ((commentariesNumber % pageSize).toInt() != 0) {
            numberOfPages += 1
        }

        return Templates.commentaries(
            CommentariesRenderingInformation(
                commentaries.map {
                    CommentaryRenderingInformation(it, _userService.getById(it.userId).login)
                },
                pageIndex + 1,
                numberOfPages,
                _groups,
                _userId,
                _requestStartTime
            )
        )
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("admin")
    @Produces(MediaType.TEXT_HTML)
    @RolesAllowed("Admin")
    fun getAdminPage(): TemplateInstance {
        val allLanguages = _programmingLanguageService.getAll()
        val allFrameworks = _frameworkService.getAll()
        val allDatabases = _databaseService.getAll()

        return Templates.admin(
            AdminPageRenderingInformation(
                allLanguages,
                allFrameworks,
                allDatabases,
                _requestStartTime
            )
        )
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("registration")
    @Produces(MediaType.TEXT_HTML)
    fun getRegistrationPage(): TemplateInstance {
        return Templates.registration()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("login")
    @Produces(MediaType.TEXT_HTML)
    fun getLoginPage(): TemplateInstance {
        return Templates.login()
    }
}