package listeners

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import services.ProjectUtilsService
import com.intellij.openapi.components.service

class ProjectStartupActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        project.service<ProjectUtilsService>()
    }
}
