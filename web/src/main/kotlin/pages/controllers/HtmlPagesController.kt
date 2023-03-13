package pages.controllers

import io.quarkus.qute.CheckedTemplate
import io.quarkus.qute.TemplateInstance
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import javax.enterprise.context.RequestScoped
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType


@RequestScoped
@Path("pages")
class HtmlPagesController {

    @CheckedTemplate
    object Templates {
        @JvmStatic
        external fun index(): TemplateInstance

        @JvmStatic
        external fun projectsAndAchievements(): TemplateInstance

        @JvmStatic
        external fun workExperience(): TemplateInstance

        @JvmStatic
        external fun workReviews(): TemplateInstance
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("index")
    @Produces(MediaType.TEXT_HTML)
    fun getIndexPage(): TemplateInstance {
        return Templates.index()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("projectsAndAchievements")
    @Produces(MediaType.TEXT_HTML)
    fun getProjectsAndAchievementsPage(): TemplateInstance {
        return Templates.projectsAndAchievements()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("workExperience")
    @Produces(MediaType.TEXT_HTML)
    fun getWorkExperiencePage(): TemplateInstance {
        return Templates.workExperience()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    @Path("workReviews")
    @Produces(MediaType.TEXT_HTML)
    fun getWorkReviewsPage(): TemplateInstance {
        return Templates.workReviews()
    }
}