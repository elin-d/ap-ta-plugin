package eu.crewmate.sdk.tc.run;

import com.intellij.execution.Location;
import com.intellij.execution.actions.ConfigurationContext;
import com.intellij.execution.actions.ConfigurationFromContext;
import com.intellij.execution.actions.LazyRunConfigurationProducer;
import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtilCore;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.testFramework.LightVirtualFile;
import com.jetbrains.python.psi.types.TypeEvalContext;
import com.jetbrains.python.run.PythonConfigurationType;
import com.jetbrains.python.run.PythonRunConfiguration;
import com.jetbrains.python.run.PythonRunConfigurationProducer;
import com.jetbrains.python.run.RunnableScriptFilter;
import eu.crewmate.sdk.common.TaSettingsState;
import eu.crewmate.sdk.tc.TcFileType;
import eu.crewmate.sdk.tc.TcLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import services.ProjectUtilsService;

import java.io.File;

public class TcRunConfigurationProducer extends LazyRunConfigurationProducer<PythonRunConfiguration> {

    private static final String launcher = "TARobot2.py";
    private static final String rootPathToSrc = ":/TestAutomation/TaRobot/src";

    private static boolean isAvailable(@NotNull final Location location, @Nullable final PsiFile script) {
        if (script == null || script.getFileType() != TcFileType.INSTANCE ||
                !script.getViewProvider().getBaseLanguage().isKindOf(TcLanguage.INSTANCE)) {
            return false;
        }
        final Module module = ModuleUtilCore.findModuleForPsiElement(script);
        if (module != null) {
            for (RunnableScriptFilter f : RunnableScriptFilter.EP_NAME.getExtensionList()) {
                // Configuration producers always called by user
                if (f.isRunnableScript(script, module, location, TypeEvalContext.userInitiated(location.getProject(), null))) {
                    return false;
                }
            }
        }
        return true;
    }

    @NotNull
    @Override
    public ConfigurationFactory getConfigurationFactory() {
        return PythonConfigurationType.getInstance().getFactory();
    }

    @Override
    protected boolean setupConfigurationFromContext(@NotNull PythonRunConfiguration configuration,
                                                    @NotNull ConfigurationContext context,
                                                    @NotNull Ref<PsiElement> sourceElement) {

        final Location location = context.getLocation();
        if (location == null) return false;
        final PsiFile script = location.getPsiElement().getContainingFile();
        if (!isAvailable(location, script)) return false;

        final VirtualFile vFile = script.getVirtualFile();
        if (vFile == null) return false;

        Project project = sourceElement.get().getProject();

        if (StringUtil.isEmpty(configuration.getWorkingDirectory())) {
            String workingDir = project.getService(ProjectUtilsService.class).getFrameworkSrcDir();
            VirtualFile vfRobotExecutable = LocalFileSystem.getInstance().findFileByPath(workingDir);
            if (vfRobotExecutable != null) configuration.setWorkingDirectory(workingDir);
            else {
                configuration.setWorkingDirectory(project.getService(ProjectUtilsService.class).getDrive() + rootPathToSrc);
            }
        }

        final Module module = ModuleUtilCore.findModuleForPsiElement(script);
        if (module != null) {
            configuration.setUseModuleSdk(true);
            configuration.setModule(module);
        }

        configuration.setScriptParameters((TaSettingsState.getInstance(project).runInHiddenMode ? "-g " : "") + "\"" + vFile.getPath() + "\"");
        configuration.setName(vFile.getName());
        configuration.setScriptName(launcher);
        return true;
    }

    @Override
    public boolean isConfigurationFromContext(@NotNull PythonRunConfiguration configuration, @NotNull ConfigurationContext context) {
        final Location location = context.getLocation();
        if (location == null) return false;
        final PsiFile script = location.getPsiElement().getContainingFile();
        if (!isAvailable(location, script)) return false;
        final VirtualFile virtualFile = script.getVirtualFile();
        if (virtualFile == null) return false;
        if (virtualFile instanceof LightVirtualFile) return false;
        final String workingDirectory = configuration.getWorkingDirectory();
        final String scriptName = configuration.getName();
        final String name = virtualFile.getName();
        return scriptName.equals(name) || virtualFile.getPath().equals(new File(workingDirectory, scriptName).getAbsolutePath());
    }

    // Only prefer over other regular config
    @Override
    public boolean isPreferredConfiguration(ConfigurationFromContext self, ConfigurationFromContext other) {
        return other.isProducedBy(PythonRunConfigurationProducer.class);
    }
}
