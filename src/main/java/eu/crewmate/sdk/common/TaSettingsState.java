package eu.crewmate.sdk.common;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Supports storing the application settings in a persistent way.
 * The {@link State} and {@link Storage} annotations define the name of the data and the file name where
 * these persistent application settings are stored.
 */
@State(
        name = "eu.crewmate.sdk.TaSettingsState",
        storages = @Storage("TaSettingsPlugin.xml")
)

public class TaSettingsState implements PersistentStateComponent<TaSettingsState> {

    public String languageId;
    public boolean runInHiddenMode = false;

    public static TaSettingsState getInstance(Project project) {
        return project.getService(TaSettingsState.class);
    }

    @Override
    public @Nullable TaSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull TaSettingsState state) {
        XmlSerializerUtil.copyBean(state, this);
    }

}
