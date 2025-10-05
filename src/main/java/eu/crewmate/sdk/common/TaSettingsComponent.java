package eu.crewmate.sdk.common;

import com.intellij.openapi.ui.ComboBox;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class TaSettingsComponent {

    private final JPanel settingsPanel;
    private final JBCheckBox hiddenMode = new JBCheckBox("Run robot in hidden mode (add \"-g\" in run configuration)");

    private final ComboBox<String> languageId = new ComboBox<>(new String[]{"deu", "eng", "fra", "slk", "none"});

    public TaSettingsComponent() {
        settingsPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("Default language for XML mapping: "), languageId, 1, false)
                .addComponent(hiddenMode, 1)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    public JPanel getPanel() {
        return settingsPanel;
    }

    public JComponent getPreferredFocusedComponent() {
        return languageId;
    }

    @NotNull
    public String getUserNameText() {
        return languageId.getItem();
    }

    public void setUserNameText(@NotNull String newText) {
        languageId.setItem(newText);
    }

    public boolean getIdeaUserStatus() {
        return hiddenMode.isSelected();
    }

    public void setIdeaUserStatus(boolean newStatus) {
        hiddenMode.setSelected(newStatus);
    }
}
