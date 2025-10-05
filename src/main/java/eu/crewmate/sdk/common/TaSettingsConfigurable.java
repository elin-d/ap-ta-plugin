package eu.crewmate.sdk.common;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;
import services.ProjectUtilsService;

import javax.swing.*;

public class TaSettingsConfigurable implements Configurable {

    private final Project project;
    private TaSettingsComponent settingsComponent;

    public TaSettingsConfigurable(Project project) {
        this.project = project;
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "TestAutomation Framework Plugin";
    }

    @Override
    public JComponent getPreferredFocusedComponent() {
        return settingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingsComponent = new TaSettingsComponent();
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        TaSettingsState settings = TaSettingsState.getInstance(project);
        boolean modified = !settingsComponent.getUserNameText().equals(settings.languageId);
        modified |= settingsComponent.getIdeaUserStatus() != settings.runInHiddenMode;
        return modified;
    }

    @Override
    public void apply() {
        TaSettingsState settings = TaSettingsState.getInstance(project);
        settings.languageId = settingsComponent.getUserNameText();
        settings.runInHiddenMode = settingsComponent.getIdeaUserStatus();
        project.getService(ProjectUtilsService.class).setLanguage(settings.languageId);
    }

    @Override
    public void reset() {
        TaSettingsState settings = TaSettingsState.getInstance(project);
        settingsComponent.setUserNameText((settings.languageId) == null ? project.getService(ProjectUtilsService.class).getPredictedLocalization() : settings.languageId);
        settingsComponent.setIdeaUserStatus(settings.runInHiddenMode);
    }

    @Override
    public void disposeUIResources() {
        settingsComponent = null;
    }
}
