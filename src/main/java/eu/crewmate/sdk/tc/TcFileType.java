package eu.crewmate.sdk.tc;

import com.intellij.openapi.fileTypes.LanguageFileType;
import eu.crewmate.sdk.common.TcsIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class TcFileType extends LanguageFileType {

    public static final TcFileType INSTANCE = new TcFileType();

    private TcFileType() {
        super(TcLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "TC File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Allplan Testcase file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "tc";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return TcsIcon.TC_ICON;

    }
}
